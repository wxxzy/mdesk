package com.example.demo

import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Scope

/**
 *
 *
 * @author wangxingxiang
 * @Description
 * @date 2019/9/19 18:42
 */
@Configuration
class RabbitConfig {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Value("\${spring.rabbitmq.host}")
    private val host: String = ""

    @Value("\${spring.rabbitmq.port}")
    private val port: Int = 0

    @Value("\${spring.rabbitmq.username}")
    private val username: String  = ""

    @Value("\${spring.rabbitmq.password}")
    private val password: String  = ""

    val EXCHANGE_A = "my-mq-exchange_A"
    val EXCHANGE_B = "my-mq-exchange_B"
    val EXCHANGE_C = "my-mq-exchange_C"


    val QUEUE_A = "QUEUE_A"
    val QUEUE_B = "QUEUE_B"
    val QUEUE_C = "QUEUE_C"

    val ROUTINGKEY_A = "spring-boot-routingKey_A"
    val ROUTINGKEY_B = "spring-boot-routingKey_B"
    val ROUTINGKEY_C = "spring-boot-routingKey_C"

    @Bean
    fun connectionFactory(): ConnectionFactory {
        val connectionFactory = CachingConnectionFactory(host, port)
        connectionFactory.setUsername(username)
        connectionFactory.setPassword(password)
        connectionFactory.virtualHost = "/"
        connectionFactory.isPublisherConfirms = true
        return connectionFactory
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    //必须是prototype类型
    fun rabbitTemplate(): RabbitTemplate {
        return RabbitTemplate(connectionFactory())
    }

    @Bean
    fun defaultExchange(): DirectExchange {
        return DirectExchange(EXCHANGE_A)
    }

    /**
     * 获取队列A
     * @return
     */
    @Bean
    fun queueA(): Queue {
        //队列持久
        return Queue(QUEUE_A, true)
    }

    @Bean
    fun binding(): Binding {

        return BindingBuilder.bind(queueA()).to(defaultExchange()).with(ROUTINGKEY_A)
    }

    @Bean
    fun queueB(): Queue {
        //队列持久
        return Queue(QUEUE_B, true)
    }

    @Bean
    fun bindingB(): Binding {
        return BindingBuilder.bind(queueB()).to(defaultExchange()).with(ROUTINGKEY_B)
    }

    @Bean
    fun queueC(): Queue {
        //队列持久
        return Queue(QUEUE_C, true)
    }

    @Bean
    fun bindingC(): Binding {
        return BindingBuilder.bind(queueC()).to(defaultExchange()).with(ROUTINGKEY_C)
    }
}