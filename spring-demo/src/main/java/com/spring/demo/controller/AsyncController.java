package com.spring.demo.controller;

import com.spring.demo.config.async.AsyncService;
import com.spring.demo.config.async.AsyncTask;
import com.spring.demo.config.async.http.AsyncVo;
import com.spring.demo.config.async.http.QueueListener;
import com.spring.demo.config.async.http.RequestQueue;
import com.spring.demo.model.dos.Computer;
import com.spring.demo.model.vos.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

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
@Slf4j
@RestController
@RequestMapping("/async")
@Api(tags = "异步调用接口测试")
public class AsyncController {

    /**
     * 异步任务
     */
    private final AsyncTask asyncTask;

    /**
     * 异步任务调用类
     */
    private final AsyncService asyncService;

    /**
     * 缓冲队列
     */
    private final RequestQueue queue;

    @Contract(pure = true)
    public AsyncController(AsyncTask asyncTask, AsyncService asyncService, RequestQueue queue) {
        this.asyncTask = asyncTask;
        this.asyncService = asyncService;
        this.queue = queue;
    }

    /**
     * 异步调用接口无返回值测试
     */
    @GetMapping("/noResponse")
    @ApiOperation("异步调用接口无返回值测试,异步请求")
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
    @ApiOperation("异步调用")
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
    @ApiOperation("异步调用，自己配置的线程池")
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
    @ApiOperation("异步调用时间测试")
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
    @ApiOperation("异步调用，自定义测试2")
    public ApiResult<List<Computer>> customer2() throws InterruptedException, ExecutionException {
        Future<List<Computer>> listFuture = asyncTask.customer11();
        Future<List<Computer>> listFuture1 = asyncTask.customer22();
        Future<List<Computer>> listFuture2 = asyncTask.customer33();
        log.info("");
        List<Computer> computers = listFuture.get();
        log.info("");
        List<Computer> computers1 = listFuture1.get();
        log.info("");
        List<Computer> computers2 = listFuture2.get();
        log.info("");
        return new ApiResult<>(Arrays.asList(computers.get(0), computers1.get(0), computers2.get(0)));
    }

    /**
     * <blockquote>
     * <pre>
     * 模拟下单处理，实现高吞吐量异步处理请求
     *
     *  1、 Controller层接口只接收请求，不进行处理，而是把请求信息放入到对应该接口的请求队列中
     * 2、 该接口对应的任务类监听对应接口的请求队列，从队列中顺序取出请求信息并进行处理
     *
     * 优点：接口几乎在收到请求的同时就已经返回，处理程序在后台异步进行处理，大大提高吞吐量
     * </pre>
     * </blockquote>
     * <p>
     * 这个是立即返回，但是数据交给哪个啥来处理呢？缓冲队列来处理，类似生产者-消费者的关系
     * {@link QueueListener} 启动任务队列，相当于监听任务队列
     */
    @GetMapping("/order")
    public DeferredResult<Object> order(String number) throws InterruptedException {
        log.info("[ OrderController ] 接到下单请求");
        log.info("当前待处理订单数： " + queue.getOrderQueue().size());

        AsyncVo<String, Object> vo = new AsyncVo<>();
        DeferredResult<Object> result = new DeferredResult<>();

        vo.setParams(number);
        vo.setResult(result);

        queue.getOrderQueue().put(vo);
        log.info("[ OrderController ] 返回下单结果");
        return result;
    }
}
