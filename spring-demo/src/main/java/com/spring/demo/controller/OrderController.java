package com.spring.demo.controller;

import com.spring.demo.config.async.http.AsyncVo;
import com.spring.demo.config.async.http.QueueListener;
import com.spring.demo.config.async.http.RequestQueue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;


/**
 * <blockquote>
 *
 * <pre>
 *
 * 模拟下单处理，实现高吞吐量异步处理请求
 *
 * 1、 Controller层接口只接收请求，不进行处理，而是把请求信息放入到对应该接口的请求队列中
 * 2、 该接口对应的任务类监听对应接口的请求队列，从队列中顺序取出请求信息并进行处理
 *
 * 优点：接口几乎在收到请求的同时就已经返回，处理程序在后台异步进行处理，大大提高吞吐量
 *
 *
 * </pre>
 *
 * </blockquote>
 *
 * @author xuweizhi
 */
@Slf4j
@RestController
@RequestMapping("/async")
public class OrderController {

    @Autowired
    private RequestQueue queue;

    /**
     * 这个是立即返回，但是数据交给哪个啥来处理呢？缓冲队列来处理，类似生产者-消费者的关系
     * {@link QueueListener} 启动任务队列，相当于监听任务队列
     * {@link }
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