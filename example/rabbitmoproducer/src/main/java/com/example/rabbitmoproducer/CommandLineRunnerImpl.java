package com.example.rabbitmoproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author wangxingxiang
 * @Description
 * @date 2019/9/19 18:10
 */
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    @Autowired
    MsgProducer msgProducer;
    @Override
    public void run(String... args) throws Exception {
        int i=1;
        while (true){
            msgProducer.sendMsg(i,"第"+i+"条消息");
            i++;
        }
    }
}
