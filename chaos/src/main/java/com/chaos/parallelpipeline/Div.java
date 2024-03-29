package com.chaos.parallelpipeline;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class Div implements Runnable {
    public static BlockingQueue<Data>  bq = new LinkedBlockingDeque<>();

    @Override
    public void run() {
        while (true){
            try {
                Data data = bq.take();
                data.setI(data.getI()/2);
                System.out.println(data.getI());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
