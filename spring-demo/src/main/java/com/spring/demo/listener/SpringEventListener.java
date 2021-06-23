package com.spring.demo.listener;

import com.spring.demo.listener.event.AsyncEvent;
import com.spring.demo.listener.event.SyncEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * Spring 事件监听机制
 *
 * @author xuweizhi
 * @since 2021/06/23 10:53
 */
@Component
public class SpringEventListener {

    @EventListener(SyncEvent.class)
    public void syncEvent(SyncEvent syncEvent) {
        System.out.printf("%s%n", syncEvent.getBusinessId());
    }

    @Async("simpleExecutor")
    @EventListener(AsyncEvent.class)
    public void syncEvent(AsyncEvent asyncEvent) {
        System.out.printf("%s%n", asyncEvent.getBusinessId());
    }
}
