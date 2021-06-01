package com.spring.demo.listener.event;

import org.springframework.context.ApplicationEvent;

/**
 * Spring 事件机制
 *
 * @author xuweizhi
 * @since 2021/06/01 17:12
 */
public class OrderSuccessEvent extends ApplicationEvent {


    public OrderSuccessEvent(Object source) {
        super(source);
    }
}
