package com.example.rabbitmoproducer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author wangxingxiang
 * @Description
 * @date 2019/9/19 18:06
 */
/*@Component
@RabbitListener(queues = {RabbitConfig.QUEUE_A,RabbitConfig.QUEUE_B})
public class MsgReceiver {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @RabbitHandler
    public void process(String content) {
        logger.info("接收处理队列A当中的消息： " + content);
    }


}*/