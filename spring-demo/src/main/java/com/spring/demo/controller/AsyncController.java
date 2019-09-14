package com.spring.demo.controller;

import com.spring.demo.config.async.AsyncService;
import com.spring.demo.config.async.AsyncTask;
import com.spring.demo.model.dos.Computer;
import com.spring.demo.model.vos.ApiResult;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * 异步调用测试
 *
 * @author xuweizhi
 * @since 2019/08/23 23:22
 */
@RestController
@RequestMapping("/async")
@Slf4j
@Api("异步调用接口测试")
public class AsyncController {

    private final AsyncTask asyncTask;

    private final AsyncService asyncService;

    @Contract(pure = true)
    public AsyncController(AsyncTask asyncTask, AsyncService asyncService) {
        this.asyncTask = asyncTask;
        this.asyncService = asyncService;
    }

    /**
     * 异步调用接口无返回值测试
     */
    @GetMapping("/noResponse")
    public void test1() throws InterruptedException {
        long start = System.currentTimeMillis();
        asyncTask.task1();
        asyncTask.task2();
        asyncTask.task3();
        long end = System.currentTimeMillis();
        log.info("task 任务总耗时:" + (end - start) + "ms");
    }

    /**
     * 异步调用有返回值测试，默认使用 SpringWeb 容器自带的线程池来实现线程的调用
     * 如何知道什么时候执行完毕和执行的结果呢？采用Future来做
     */
    @GetMapping("/responseWithSpringFrame")
    public String test2() throws InterruptedException {
        long start = System.currentTimeMillis();
        Future<String> task4 = asyncTask.task4();
        Future<String> task5 = asyncTask.task5();
        Future<String> task6 = asyncTask.task6();

        String result = null;
        for (; ; ) {
            if (task4.isDone() && task5.isDone() && task6.isDone()) {
                break;
            }
            Thread.sleep(1000);
        }
        long end = System.currentTimeMillis();
        log.info("task 任务总耗时: " + (end - start) + " ms");
        return "task 任务总耗时: " + (end - start) + " ms";
    }

    /**
     * 可以执行任务拆解的方式 哈哈哈
     * <p>
     * 异步调用，使用自带的线程池来执行异步任务
     */
    @GetMapping("/responseWithCustomerThreadPool")
    public Integer test3() throws Exception {
        long start = System.currentTimeMillis();
        Future<Integer> future1 = asyncService.methodB();
        Future<Integer> future2 = asyncService.methodC();
        Future<Integer> future3 = asyncService.methodD();
        Integer x = future1.get();
        Integer y = future2.get();
        Integer z = future3.get();
        long end = System.currentTimeMillis();
        System.out.println("耗时：" + (end - start));
        return x + y + z;
    }

    /**
     * 经过测试多个异步任务调度并发执行，取最长的时间进行阻塞......
     */
    @GetMapping("/customer1")
    public void customer1() throws InterruptedException, ExecutionException {

        Future<List<Computer>> listFuture = asyncTask.customer1();
        Future<List<Computer>> listFuture1 = asyncTask.customer2();
        Future<List<Computer>> listFuture2 = asyncTask.customer3();

        log.info("111111111111111111111111111");
        listFuture2.get();
        log.info("222222222222222222222222222");
        listFuture1.get();
        log.info("333333333333333333333333333");
        listFuture.get();
        log.info("444444444444444444444444444");
    }

    @GetMapping("/customer2")
    public ApiResult<List<Computer>> customer2() throws InterruptedException, ExecutionException {

        Future<List<Computer>> listFuture = asyncTask.customer11();
        Future<List<Computer>> listFuture1 = asyncTask.customer22();
        Future<List<Computer>> listFuture2 = asyncTask.customer33();

        log.info("111111111111111111111111111");
        List<Computer> computers = listFuture.get();
        log.info("222222222222222222222222222");
        List<Computer> computers1 = listFuture1.get();
        log.info("333333333333333333333333333");
        List<Computer> computers2 = listFuture2.get();
        log.info("444444444444444444444444444");
        return new ApiResult<>(Arrays.asList(computers.get(0), computers1.get(0), computers2.get(0)));
    }
}
