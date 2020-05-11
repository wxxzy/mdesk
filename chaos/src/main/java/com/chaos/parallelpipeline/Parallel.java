package com.chaos.parallelpipeline;

public class Parallel {
    public static void main(String[] args) {
        new Thread(new Plus()).start();
        new Thread(new Multiply()).start();
        new Thread(new Div()).start();

        final long start = System.currentTimeMillis();
        for (int i = 1; i < 1000; i++) {
            for (int j = 1; j < 1000; j++) {
                Data data = new Data();
                data.setI(i);
                data.setJ(j);
                Plus.bq.add(data);
            }
        }
        System.out.println("total"+ (System.currentTimeMillis() - start));
    }
}
