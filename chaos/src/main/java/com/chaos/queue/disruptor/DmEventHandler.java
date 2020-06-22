package com.chaos.queue.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.WorkHandler;

import java.util.UUID;

public class DmEventHandler implements EventHandler<DmEvent>, WorkHandler<DmEvent> {
    @Override
    public void onEvent(DmEvent dmEvent, long l, boolean b) throws Exception {
        this.onEvent(dmEvent);
    }

    @Override
    public void onEvent(DmEvent dmEvent) throws Exception {
        //这里做具体的消费逻辑
        dmEvent.setIndicatorCode(UUID.randomUUID().toString());//简单生成下ID

        System.out.println(dmEvent.toString());
    }
}
