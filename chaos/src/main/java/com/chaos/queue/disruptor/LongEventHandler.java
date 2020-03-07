package com.chaos.queue.disruptor;

import com.lmax.disruptor.WorkHandler;

/**
 * 消费者
 */
public class LongEventHandler implements WorkHandler<LongEvent> {

    @Override
    public void onEvent(LongEvent longEvent) throws Exception {
        System.out.println(Thread.currentThread().getName() + ":Event: " + longEvent.get());
    }
}
