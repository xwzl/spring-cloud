package com.dftc.microservice.health.common.component.mq.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ：huanghongfa
 * @date ：Created in 2019/7/24 0024 16:08
 * @description：消息主题枚举
 */
@AllArgsConstructor
@Getter
public enum TopIcEnum {
    ORDER_MESSAGE(0, "order-topic", "订单类消息"), PUSH_MESSAGE(1, "push-topic", "推送类消息"),SETTLEMENT_MESSAGE(1,"settlement-topic","结算类消息"),
    SERVICE_DM(3, "service-dm", "dm消息"), PAY_OTHER(3, "pay_other", "代付延时消息"), SERVICE_ORDER(3, "service-om", "om消息");

    private int type;
    private String topic;
    private String describe;

    public static TopIcEnum check(int type) {
        for (TopIcEnum c : TopIcEnum.values()) {
            if (c.getType() == type) {
                return c;
            }
        }
        throw new RuntimeException("消息主题不存在");
    }

}
