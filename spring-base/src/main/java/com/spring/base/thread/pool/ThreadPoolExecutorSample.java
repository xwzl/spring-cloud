package com.spring.base.thread.pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author xuweizhi
 */
public class ThreadPoolExecutorSample {

    public static void main(String[] args) {

        // 构造器方式构造线程池
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(2, 3, 60, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(1),
                new ThreadPoolExecutor.DiscardOldestPolicy());

        // 循环产生多个任务，并将其加入到线程池去执行
        for (int i = 0; i < 5; i++) {
            try {
                threadPool.execute(new ThreadPoolSample(1, threadPool));
                // 便于观察，延时
                Thread.sleep(1000);
            } catch (Exception e) {
                System.out.println("异常：" + e.getMessage());
                e.printStackTrace();
            }
        }
        System.out.println("主线程开始检测线程池活动线程个数...");
        // 总共检测20秒，每隔2秒检测一次
        // 当活动线程为0(即任务执行完毕)时，关闭线程池
        for (int i = 0; i < 10; i++) {
            System.out.println("主线程判断池中正活动线程数：" + threadPool.getActiveCount());
            System.out.println("主线程判断池中已完成线程数：" + threadPool.getCompletedTaskCount());
            if (threadPool.getActiveCount() <= 0) {
                threadPool.shutdown();
                break;
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // 如果上面循环检测没有关闭线程池，则最后强制关闭一次
        if (threadPool.isTerminated()) {
            System.out.println("强制关闭线程池....");
            threadPool.shutdownNow();
        }
    }
}
