package com.spring.base.thread.disruptor;

import org.jetbrains.annotations.Contract;

/**
 * 除了使用 CAS 和提供了各种不同的等待策略来提高系统的吞吐量之外，Disruptor 框架大有将优化进行到底的气势，它甚至会尝试解决 CPU 缓存的伪共享
 * 问题。
 * <p>
 * 什么是伪共享问题呢？我们知道，为了提高 CPU 的速度，CPU 有一个高速缓存 Cache.在高速缓存中，读写数据的最小单位为缓存行（Cache line）,它是从
 * 主内存（Memory）复制到缓存（Cache）的最小单位，一般为 32 字节到 128 字节。
 * <p>
 * 当两个变量存放在一个缓存时，在多线程访问中，可能会影响彼此的性能。假设变量 x 和 y 同在一个缓存行，运行在 CPU1 上的线程更新了变量 x,那么 CPU2
 * 上的缓存行就会失效，同一行的变量 Y 即使没有修改也会变成无效，导致 Cache 无法命中。接着，如果 CPU2 上的线程更新了变量 Y,导致 CPU1 上的缓存行
 * 失效（此时，同一行的变量 X 变得无法访问）。这种情况反复发生，无疑是一个潜在的性能杀手，如果 CPU 经常不能命中缓存，那么系统的吞吐量就会急剧下降。
 * <p>
 * 为了避免这种情况发生，一种可行的 做法就是在变量 X 的前后空间都先占据一定的位置（把它叫做 padding,用来填充用的）。这样当内存被读入缓存时，这个缓
 * 存中，只有变量 X 一个变量实际是有效的，因此就不会发生多个线程同时修改缓存中不同变量而导致变量全体失效的情况：
 *
 * @author xuweizhi
 */
public final class FalseSharing implements Runnable {

    /**
     * 物理机器的 CPU 数量
     */
    private final static int NUM_THREADS = 2;

    private final static long ITERATIONS = 500L * 1000L * 1000L;

    /**
     * 设置数组大小
     */
    private final int arrayIndex;

    /**
     *
     */
    private static VolatileLong[] longs = new VolatileLong[NUM_THREADS];

    static {
        for (int i = 0; i < longs.length; i++) {
            longs[i] = new VolatileLong();
        }
    }

    @Contract(pure = true)
    private FalseSharing(final int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(final String[] args) throws Exception {
        final long start = System.currentTimeMillis();
        runTest();
        System.out.println("duration = " + (System.currentTimeMillis() - start));
    }

    private static void runTest() throws InterruptedException {
        Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new FalseSharing(i));
        }

        for (Thread t : threads) {
            t.start();
        }

        for (Thread t : threads) {
            t.join();
        }
    }

    /**
     * 模拟两个线程之间互相对一个变量进行修改，利用对象中多余的字段，把 value 不放入到同一个缓存行
     */
    @Override
    public void run() {
        long i = ITERATIONS + 1;
        while (0 != --i) {
            longs[arrayIndex].value = i;
        }
    }

    /**
     * JDK 7 某些版本 和 JDK 8中 会把不用的数据优化 导致 这种优化手段失效
     * Unlock: -XX:-RestrictContended (JDK 8  option)
     */
    public final static class VolatileLong {

        volatile long value = 0L;

        /**
         * 用于分开 VolatileLong 数组中第一个和第二个元素的值分割，防止放入同一个缓存行，注释后运行特别慢
         */
        public long p1, p2, p3, p4, p5, p6, p7 = 8L;

    }
}
