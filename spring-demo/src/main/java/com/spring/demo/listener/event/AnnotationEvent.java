package com.spring.demo.listener.event;

import org.springframework.context.ApplicationEvent;

/**
 * @author xuweizhi
 * @since 2021/06/03 23:50
 */
public class AnnotationEvent extends ApplicationEvent {

    private String msg;

    public AnnotationEvent(Object source, String msg) {
        super(source);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
