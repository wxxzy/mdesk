package com.chaos.parallelpipeline;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Multiply implements Runnable {
    public static BlockingQueue<Data> bq = new LinkedBlockingDeque<>();

    @Override
    public void run() {
        while (true) {
            try {
                Data data = bq.take();
                data.setI(data.getI() * data.getJ());
                Div.bq.add(data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
