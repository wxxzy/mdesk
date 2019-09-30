package com.example.demo

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component

/**
 *
 *
 * @author wangxingxiang
 * @Description
 * @date 2019/9/19 18:47C
 */
@Component
@RabbitListener(queues = ["QUEUE_A", "QUEUE_B","QUEUE_C"])
class MsgReceiver {
    private val logger = LoggerFactory.getLogger(this.javaClass)

    @RabbitHandler
    fun process(content: String) {
        logger.info("接收处理队列A当中的消息： $content")
    }
}