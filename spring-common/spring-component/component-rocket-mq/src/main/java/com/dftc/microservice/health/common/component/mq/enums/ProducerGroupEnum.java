package com.dftc.microservice.health.common.component.mq.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author ：huanghongfa
 * @date ：Created in 2019/7/24 0024 16:48
 * @description：生产消息组
 */
@AllArgsConstructor
@Getter
public enum ProducerGroupEnum {
    CAS(0, "cas", "认证中心"), DM(0, "dm", "字典"), IM(0, "im", "即时通讯"), OM(0, "om", "订单"), PUSH(0, "push", "推送"),
    TASK(0, "task", "定时任务"), TM(0, "tm", "终端"), UM(0, "um", "用户管理"), WM(0, "wm", "仓储");

    private int type;
    private String groupName;
    private String describe;

    public static ProducerGroupEnum check(int type) {
        for (ProducerGroupEnum c : ProducerGroupEnum.values()) {
            if (c.getType() == type) {
                return c;
            }
        }
        throw new RuntimeException("生产消息组不存在");
    }
}
