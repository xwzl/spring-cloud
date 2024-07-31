package com.spring.demo.config.async;

import com.spring.demo.model.dos.Computer;
import com.spring.demo.service.ComputerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.util.List;
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

    @Resource
    private ComputerService computerService;

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

    @Async
    public Future<List<Computer>> customer1() throws InterruptedException {
        log.info("customer1");
        Thread.sleep(1000);
        log.info("customer11");
        return new AsyncResult<>(null);
    }

    @Async
    public Future<List<Computer>> customer2() throws InterruptedException {
        log.info("customer2");
        Thread.sleep(2000);
        log.info("customer22");
        return new AsyncResult<>(null);
    }

    @Async
    public Future<List<Computer>> customer3() throws InterruptedException {
        log.info("customer3");
        Thread.sleep(3000);
        log.info("customer33");
        return new AsyncResult<>(null);
    }

    @Async
    public Future<List<Computer>> customer11() throws InterruptedException {
        log.info("customer1");
        Thread.sleep(1000);
        log.info("customer11");
        return new AsyncResult<>(computerService.list());
    }

    @Async
    public Future<List<Computer>> customer22() throws InterruptedException {
        log.info("customer2");
        Thread.sleep(2000);
        log.info("customer22");
        return new AsyncResult<>(computerService.list());

    }

    @Async
    public Future<List<Computer>> customer33() throws InterruptedException {
        log.info("customer3");
        Thread.sleep(3000);
        log.info("customer33");
        return new AsyncResult<>(computerService.list());
    }
}
