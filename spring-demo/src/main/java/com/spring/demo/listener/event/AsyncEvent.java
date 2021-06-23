package com.spring.demo.listener.event;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

/**
 * 异步事件调用
 *
 * @author xuweizhi
 * @since 2021/06/23 10:47
 */
@Setter
@Getter
public class AsyncEvent extends ApplicationEvent {

    /**
     * 业务主键 id
     */
    private String businessId;

    public AsyncEvent(String businessId) {
        super(new Object());
        this.businessId = businessId;
    }
}
