package com.spring.demo.controller;

import com.spring.common.model.common.ApiResult;
import com.spring.demo.listener.event.OrderSuccessEvent;
import io.swagger.annotations.Api;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Spring 事件监听机制
 *
 * @author xuweizhi
 * @since 2021/06/01 17:09
 */
@RestController
@RequestMapping("/spring")
@Api(tags = "Spring 事件监听机制")
public class SpringEventController {

    @Resource
    private ApplicationContext applicationContext;

    /**
     * 事件监听机制
     *
     * @return 结果
     */
    @GetMapping("order")
    public ApiResult<String> order(){
        applicationContext.publishEvent(new OrderSuccessEvent(this));
        return new ApiResult<>("下单成功");
    }
}
