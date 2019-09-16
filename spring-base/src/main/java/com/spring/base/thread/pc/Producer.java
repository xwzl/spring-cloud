package com.spring.base.thread.pc;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuweizhi
 */
public class Producer implements Runnable {

    private volatile boolean isRunning = true;

    private BlockingQueue<PcData> queue;

    private static AtomicInteger count = new AtomicInteger();

    private static final int SLEEP_TIME = 1000;

    Producer(BlockingQueue<PcData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        PcData data = null;
        Random r = new Random();

        System.out.println("start producer id=" + Thread.currentThread().getId());
        try {
            while (isRunning) {
                Thread.sleep(r.nextInt(SLEEP_TIME));
                data = new PcData(count.incrementAndGet());
                System.out.println(data + " is put into queue");
                // queue 队列中添加数据
                if (!queue.offer(data, 2, TimeUnit.SECONDS)) {
                    System.err.println("failed to put data：" + data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }

    public void stop() {
        isRunning = false;
    }
}
