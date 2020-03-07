package com.chaos.queue.disruptor;

import com.lmax.disruptor.EventHandler;

/**
 * 消费者
 */
public class LongEventHandler implements EventHandler<LongEvent> {
    @Override
    public void onEvent(LongEvent longEvent, long l, boolean b) throws Exception {
        System.out.println("Event: " + longEvent.get());
    }
}
