package com.chaos.parallelpipeline;


public class Serial {
    public static void main(String[] args) {
        final long start = System.currentTimeMillis();
        for (int i = 1; i < 1000; i++) {
            for (int j = 1; j < 1000; j++) {
                long result = (i + j) * i/2;
                System.out.println("(" +i + "+" + j +")*" + i +"/2 = " + result );
            }
        }
        System.out.println(System.currentTimeMillis() - start);
    }
}
