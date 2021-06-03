package com.spring.demo.listener.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * Spring 事件机制
 *
 * @author xuweizhi
 * @since 2021/06/01 17:12
 */
@Slf4j
@Component
public class SmsService implements ApplicationListener<OrderSuccessEvent> {

    @Override
    public void onApplicationEvent(OrderSuccessEvent event) {
        log.info(event.toString());
        this.sendSms();
    }

    /**
     * 发送短信
     */
    public void sendSms() {
        System.out.println("发送短信...");
    }

}
