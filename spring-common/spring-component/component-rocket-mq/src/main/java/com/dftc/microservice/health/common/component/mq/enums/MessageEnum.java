package com.dftc.microservice.health.common.component.mq.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ：huanghongfa
 * @date ：Created in 2019/7/24 0024 16:53
 * @description：消息类型枚举类
 */
@AllArgsConstructor
@Getter
public enum MessageEnum {

    COMMON_MESSAGE(0, "普通类型信息"), SEQUENCE_MESSAGE(1, "顺序消息"), AFFIAR_MESSAGE(2, "事务消息");

    private int type;
    private String describe;

    public static MessageEnum check(int type) {
        for (MessageEnum c : MessageEnum.values()) {
            if (c.getType() == type) {
                return c;
            }
        }
        throw new RuntimeException("消息类型不存在");
    }
}
