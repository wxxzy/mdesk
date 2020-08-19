package com.chaos.queue.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * 数据产生工程
 * 会在Disruptor系统初书化时，构建所有缓冲区中的数据
 */
public class DmEventFactory implements EventFactory<DmEvent> {
    @Override
    public DmEvent newInstance() {
        return new DmEvent();
    }
}
