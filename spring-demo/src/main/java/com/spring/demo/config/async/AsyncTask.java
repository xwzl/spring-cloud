package com.spring.demo.config.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * 异步调用执行类
 *
 * @author xuweizhi
 * @since 2019/08/23 23:20
 */
@Component
@Slf4j
public class AsyncTask {

    @Async
    public void task1() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        log.info("task1 任务耗时: " + (end - start) + " ms");
    }

    @Async
    public void task2() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(2000);
        long end = System.currentTimeMillis();
        log.info("task2 任务耗时: " + (end - start) + " ms");
    }

    @Async
    public void task3() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(3000);
        long end = System.currentTimeMillis();
        log.info("task3 任务耗时: " + (end - start) + " ms");
    }

    @Async
    public Future<String> task4() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();
        log.info("task1 任务耗时: " + (end - start) + " ms");
        return new AsyncResult<>("task1任务执行完毕");
    }

    @Async
    public Future<String> task5() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(2000);
        long end = System.currentTimeMillis();
        log.info("task2 任务耗时: " + (end - start) + " ms");
        return new AsyncResult<>("task2任务执行完毕");
    }

    @Async
    public Future<String> task6() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread.sleep(3000);
        long end = System.currentTimeMillis();
        log.info("task3 任务耗时: " + (end - start) + " ms");
        return new AsyncResult<>("task3任务执行完毕");
    }
}
