package com.dftc.microservice.health.common.component.mq;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import com.spring.component.api.version.VersionContent;
import com.spring.component.api.version.VersionUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.validation.annotation.Validated;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xingxin
 * @createTime 2019/9/2
 */
@Slf4j
@Validated
public class MQSender {

    private final RocketMQTemplate rocketMQTemplate;

    public MQSender(RocketMQTemplate rocketMQTemplate) {
        this.rocketMQTemplate = Objects.requireNonNull(rocketMQTemplate);
    }

    /**
     * 发送普通消息
     *
     * @param topic
     *            主题
     * @param tag
     *            标签
     * @param payload
     *            消息内容[json]
     */
    public void send(String topic, String tag, String payload) {
        String destination = parserDestination(topic, tag);
        rocketMQTemplate.send(destination, MessageBuilder.withPayload(payload)
            .setHeader(VersionContent.VERSION_HEADER_KEY, VersionUtil.getCurrentApiVersion()).build());
    }

    /**
     * 异步发送
     *
     * @param topic
     *            主题
     * @param tag
     *            标签
     * @param payload
     *            消息内容[json]
     * @param sendCallback
     *            回调
     */
    public void sendAsync(String topic, String tag, String payload, SendCallback sendCallback) {
        String destination = parserDestination(topic, tag);
        rocketMQTemplate.asyncSend(destination,
            MessageBuilder.withPayload(payload)
                .setHeader(VersionContent.VERSION_HEADER_KEY, VersionUtil.getCurrentApiVersion()).build(),
            sendCallback);
    }

    public void sendDelay(String topic, String tag, String payload, DelayLevel level) {
        org.apache.rocketmq.common.message.Message message =
            new org.apache.rocketmq.common.message.Message(topic, tag, payload.getBytes(StandardCharsets.UTF_8));
        message.setDelayTimeLevel(level.value);
        message.putUserProperty(VersionContent.VERSION_HEADER_KEY, VersionUtil.getCurrentApiVersion());
        try {
            SendResult result = rocketMQTemplate.getProducer().send(message);
            if (!result.getSendStatus().equals(SendStatus.SEND_OK)) {
                throw new RuntimeException("send failed" + result.getSendStatus());
            }
        } catch (Exception e) {
            throw new RuntimeException("send failed", e);
        }
    }

    public void sendDelay(String topic, String tag, String payload, Integer level) {
        org.apache.rocketmq.common.message.Message message =
            new org.apache.rocketmq.common.message.Message(topic, tag, payload.getBytes(StandardCharsets.UTF_8));
        message.setDelayTimeLevel(level);
        message.putUserProperty(VersionContent.VERSION_HEADER_KEY, VersionUtil.getCurrentApiVersion());
        try {
            SendResult result = rocketMQTemplate.getProducer().send(message);
            if (!result.getSendStatus().equals(SendStatus.SEND_OK)) {
                throw new RuntimeException("send failed" + result.getSendStatus());
            }
        } catch (Exception e) {
            throw new RuntimeException("send failed", e);
        }
    }

    private String parserDestination(String topic, String tag) {
        Objects.requireNonNull(topic);
        return topic.concat(StringUtils.isEmpty(tag) ? "" : ":".concat(tag));
    }

    public enum TransactionProducerGroup {
        SERVICE_OM_DDKY_ORDER_PROCESSOR;
    }

    @Getter
    public enum DelayLevel {
        // 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
        l1(1, TimeUnit.SECONDS.toMillis(1)), l2(2, TimeUnit.SECONDS.toMillis(5)), l3(3, TimeUnit.SECONDS.toMillis(10)),
        l4(4, TimeUnit.SECONDS.toMillis(30)), l5(5, TimeUnit.MINUTES.toMillis(1)), l6(6, TimeUnit.MINUTES.toMillis(2)),
        l7(7, TimeUnit.MINUTES.toMillis(3)), l8(8, TimeUnit.MINUTES.toMillis(4)), l9(9, TimeUnit.MINUTES.toMillis(5)),
        l10(10, TimeUnit.MINUTES.toMillis(6)), l11(11, TimeUnit.MINUTES.toMillis(7)),
        l12(12, TimeUnit.MINUTES.toMillis(8)), l13(13, TimeUnit.MINUTES.toMillis(9)),
        l14(14, TimeUnit.MINUTES.toMillis(10)), l15(15, TimeUnit.MINUTES.toMillis(20)),
        l16(16, TimeUnit.MINUTES.toMillis(30)), l17(17, TimeUnit.HOURS.toMillis(1)),
        l18(18, TimeUnit.HOURS.toMillis(2));

        private int value;
        private long time;

        DelayLevel(int value, long time) {
            this.value = value;
            this.time = time;
        }

        public static DelayLevel valueOf(int value) {
            for (DelayLevel dl : DelayLevel.values()) {
                if (dl.value == value) {
                    return dl;
                }
            }
            return null;
        }
    }
}
