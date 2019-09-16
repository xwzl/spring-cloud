package com.spring.demo.untils.pool;

import org.jetbrains.annotations.Contract;

import java.io.Serializable;
import java.util.concurrent.ThreadPoolExecutor;

/**
 *  线程池任务执行类
 *  
 *
 * @author xuweizhi
 */
public class ThreadPoolSample implements Runnable, Serializable {

    /**
     * threadPoolTaskData：模拟调用线程传入的数据
     * threadPool：为了统计信息而传入进来的
     */
    private Integer threadPoolTaskData;

    private ThreadPoolExecutor threadPool;

    @Contract(pure = true)
    ThreadPoolSample(Integer tasks, ThreadPoolExecutor threadPool) {
        this.threadPoolTaskData = tasks;
        this.threadPool = threadPool;
    }

    @Override
    public void run() {
        System.out.println("------------------------------");
        System.out.println("曾计划执行的近似任务总数:" + threadPool.getTaskCount());
        System.out.println("已完成执行的近似任务总数:" + threadPool.getCompletedTaskCount());
        System.out.println("池中曾出现过的最大线程数:" + threadPool.getLargestPoolSize());
        System.out.println("返回线程池中的当前线程数:" + threadPool.getPoolSize());
        System.out.println("线程池中的当前活动线程数:" + threadPool.getActiveCount());
        System.out.println("线程池中约定的核心线程数:" + threadPool.getCorePoolSize());
        System.out.println("线程池中约定的最大线程数:" + threadPool.getMaximumPoolSize());
        for (int i = 0; i < 2; i++) {
            System.out.println("--------线程名:" + Thread.currentThread().getName() + ":" + (threadPoolTaskData++));
            try {
                //用延时来模拟线程在操作
                Thread.sleep(2000);
            } catch (Exception e) {
                System.out.println("支线程异常：" + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}

