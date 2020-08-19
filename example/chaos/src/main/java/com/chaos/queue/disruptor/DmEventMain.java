package com.chaos.queue.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.UUID;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class DmEventMain {
    public static void main(String[] args) throws Exception {
        DmEventFactory factory = new DmEventFactory();
        //Executor executor = Executors.newCachedThreadPool();
        ThreadFactory threadFactory = new ThreadFactory() {
            private final AtomicInteger index = new AtomicInteger(1);
            @Override
            public Thread newThread(Runnable r) {
                return new Thread((ThreadGroup) null, r, "dm-thread-" + index.getAndIncrement());
            }
        };

        // 设置缓冲区大小, 必须是2的整数次幂.
        int bufferSize = 1024;
        // 构建Disruptor对象
        Disruptor<DmEvent> disruptor = new Disruptor<DmEvent>(factory,
                bufferSize,
                //executor,
                threadFactory,
                ProducerType.MULTI,
                new BlockingWaitStrategy()
        );

        //设置消费者
        disruptor.handleEventsWithWorkerPool(
                new DmEventHandler());
        // 启动并初始化Disruptor
        disruptor.start();
        RingBuffer<DmEvent> ringBuffer = disruptor.getRingBuffer();
        //生产者
        DmEventProducerWithTranslator producer = new DmEventProducerWithTranslator(ringBuffer);
        //ByteBuffer bb = ByteBuffer.allocate(8);
        DmEvent dm = new DmEvent();
        //不断向生产者不断向缓冲区写入数据
        for (long l = 0; true; l++) {
            //bb.putLong(0, l);
            dm.setIndicatorValue(l);
            dm.setIndicatorCode(UUID.randomUUID().toString());
            //bb.put
            producer.onData(dm);
            Thread.sleep(1000);
        }

    }
}
