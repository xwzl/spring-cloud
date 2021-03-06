package com.dftc.microservice.health.common.component.mq.callback;

import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ：huanghongfa
 * @date ：Created in 2019/7/25 0025 18:05 @description：
 */
@Slf4j
public class AsyncSendCallBack implements SendCallback {
    @Override
    public void onSuccess(SendResult sendResult) {
        log.info("异步发送普通消息成功：{}", sendResult.toString());
    }

    @Override
    public void onException(Throwable throwable) {
        log.error("异步发送普通消息失败：{}", throwable.toString());
    }
}
