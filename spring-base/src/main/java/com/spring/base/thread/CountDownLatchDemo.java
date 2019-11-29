package com.spring.base.thread;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 计数器，等待指定数量的线程执行完毕，然后进行下一步的任务
 *
 * @author xuweizhi
 * @since 2019/09/02 11:27
 */
public class CountDownLatchDemo implements Runnable {

    /**
     * 设置等待的线程完成数或者那个啥
     */
    private static final CountDownLatch end = new CountDownLatch(10);

    private static final CountDownLatchDemo demo = new CountDownLatchDemo();

    @Override
    public void run() {
        try {
            //模拟检查任务
            Thread.sleep(new Random().nextInt(10) * 1000);
            System.out.println("check complete" + Thread.currentThread().getName());
            // 这里进行加一
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ExecutorService exec = new ThreadPoolExecutor(10, 20, 100, TimeUnit.MILLISECONDS, new ArrayBlockingQueue<>(10), r -> new Thread(r, "这是一个线程啊"));
        for (int i = 0; i < 10; i++) {
            exec.submit(demo);
        }
        //等待检查，达到10个以后进行之后的任务
        end.await();
        //发射火箭
        System.out.println("Fire!");
        exec.shutdown();
    }
}
