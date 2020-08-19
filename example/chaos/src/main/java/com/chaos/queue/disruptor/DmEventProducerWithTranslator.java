package com.chaos.queue.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

/**
 * 生产者
 */
public class DmEventProducerWithTranslator {
    private final RingBuffer<DmEvent> ringBuffer;

    public DmEventProducerWithTranslator(RingBuffer<DmEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    private static final EventTranslatorOneArg<DmEvent, DmEvent> TRANSLATOR =
            new EventTranslatorOneArg<DmEvent, DmEvent>() {
                public void translateTo(DmEvent event, long sequence, DmEvent bb) {
                    event.setIndicatorValue(bb.getIndicatorValue());
                    event.setIndicatorCode(bb.getIndicatorCode());
                }
            };

    public void onData(DmEvent bb) {
        ringBuffer.publishEvent(TRANSLATOR, bb);
    }
}
