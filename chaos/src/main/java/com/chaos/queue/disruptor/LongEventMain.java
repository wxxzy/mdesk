package com.chaos.queue.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 单生产者，多消费者
 */
public class LongEventMain {
    public static void main(String[] args) throws Exception {
        //
        LongEventFactory factory = new LongEventFactory();
        Executor executor = Executors.newCachedThreadPool();
        ThreadFactory threadFactory = new ThreadFactory() {
            private final AtomicInteger index = new AtomicInteger(1);
            @Override
            public Thread newThread(Runnable r) {
                return new Thread((ThreadGroup) null, r, "disruptor-thread-" + index.getAndIncrement());
            }
        };

        // 设置缓冲区大小, 必须是2的整数次幂.
        int bufferSize = 1024;
        // 构建Disruptor对象
        Disruptor<LongEvent> disruptor = new Disruptor<LongEvent>(factory,
                bufferSize,
                //executor,
                threadFactory,
                ProducerType.MULTI,
                new BlockingWaitStrategy()
        );
        //设置消费者
        disruptor.handleEventsWithWorkerPool(
                new LongEventHandler(),
                new LongEventHandler(),
                new LongEventHandler(),
                new LongEventHandler(),
                new LongEventHandler()).thenHandleEventsWithWorkerPool(new LongEventHandler(),new LongEventHandler());
        // 启动并初始化Disruptor
        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
        LongEventProducerWithTranslator producer = new LongEventProducerWithTranslator(ringBuffer);
        ByteBuffer bb = ByteBuffer.allocate(8);
        //不断向生产者不断向缓冲区写入数据
        for (long l = 0; true; l++) {
            bb.putLong(0, l);
            producer.onData(bb);
            Thread.sleep(1000);
        }
    }
}
