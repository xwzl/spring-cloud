package com.spring.demo.controller;

import com.spring.common.model.common.ResultVO;
import com.spring.demo.listener.event.AnnotationEvent;
import com.spring.demo.listener.event.AsyncEvent;
import com.spring.demo.listener.event.OrderSuccessEvent;
import com.spring.demo.listener.event.SyncEvent;
import jakarta.annotation.Resource;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Spring 事件监听机制
 *
 * @author xuweizhi
 * @since 2021/06/01 17:09
 */
@RestController
@RequestMapping("/spring")
//@Api(tags = "Spring 事件监听机制")
public class SpringEventController {

    @Resource
    private ApplicationContext applicationContext;

    /**
     * 事件监听机制
     *
     * @return 结果
     */
    @GetMapping("order")
    public ResultVO<String> order() {
        applicationContext.publishEvent(new OrderSuccessEvent(this));
        return new ResultVO<>("下单成功");
    }

    @GetMapping("syncEvent")
    public ResultVO<String> syncEvent() {
        applicationContext.publishEvent(new SyncEvent("Rock you!"));
        return new ResultVO<>("同步调用");
    }

    @GetMapping("asyncEvent")
    public ResultVO<String> asyncEvent() {
        applicationContext.publishEvent(new AsyncEvent("Rock you a!"));
        System.out.println("异步调用");
        return new ResultVO<>("同步调用");
    }


    /**
     * 注解事件通知
     */
    @GetMapping("annotationEvent")
    public ResultVO<String> annotationEvent(){
        applicationContext.publishEvent(new AnnotationEvent(this,"Spring Annotation 通知"));
        return new ResultVO<>("下单成功");
    }
}
