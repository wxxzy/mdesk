package com.chaos.cpucache;

import java.util.Arrays;

public class CpuCacheShare {
    public static void main(String[] args) throws InterruptedException {
        final long start = System.currentTimeMillis();
        runTest();
        final long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[FalseSharing.NUM_THREADS];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharing(i));
        }
        for (Thread t :
                threads) {
            t.start();
        }
        for (Thread t :
                threads) {
            t.join();
        }
    }

}

