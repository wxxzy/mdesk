package com.chaos.cpucache;

public final class FalseSharing implements Runnable{
    //grep 'core id' /proc/cpuinfo | sort -u | wc -l
    public final static int NUM_THREADS = 2;
    public final static long ITERATIONS = 500L * 100L * 100L;
    private final int arrayIndex;

    public FalseSharing(final int arrayIndex){
        this.arrayIndex = arrayIndex;
    }

    private static VolatileLong[] longs =new VolatileLong[NUM_THREADS];

    static {
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new VolatileLong();
        }
    }

    @Override
    public void run() {
        long i = ITERATIONS +1;
        while (0 != --i){
            longs[arrayIndex].value = i;
        }
    }
}
