package com.spring.demo.controller;

import com.spring.demo.config.async.AsyncService;
import com.spring.demo.config.async.AsyncTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
public class AsyncController {

    @Autowired
    private AsyncTask asyncTask;

    @Autowired
    private AsyncService asyncService;

    @GetMapping("/test1")
    public void test1() throws InterruptedException {
        long start = System.currentTimeMillis();
        asyncTask.task1();
        asyncTask.task2();
        asyncTask.task3();
        long end = System.currentTimeMillis();
        log.info("task 任务总耗时:" + (end - start) + "ms");
    }

    /**
     * 如何知道什么时候执行完毕和执行的结果呢？采用Future来做
     */
    @GetMapping("/test2")
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

    @GetMapping("test3")
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

}
