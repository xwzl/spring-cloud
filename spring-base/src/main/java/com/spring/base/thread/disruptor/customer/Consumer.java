package com.spring.base.thread.disruptor.customer;

import java.text.MessageFormat;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @author xuweizhi
 */
public class Consumer implements Runnable {

    private BlockingQueue<PcData> queue;

    private static final int SLEEP_TIME = 1000;

    Consumer(BlockingQueue<PcData> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        System.out.println("start Consumer id=" + Thread.currentThread().getId());
        Random r = new Random();
        try {
            while (true) {
                PcData data = queue.take();
                if (null == data) {
                    continue;
                }
                int re = data.getData() * data.getData();
                System.out.println(MessageFormat.format("{0}*{1}={2}", data.getData(), data.getData(), re));
                Thread.sleep(r.nextInt(SLEEP_TIME));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
