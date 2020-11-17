package com.dftc.microservice.health.common.component.mq.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

/**
 * @author ：huanghongfa
 * @date ：Created in 2019/7/24 0024 16:24
 * @description：标签枚举
 */
@AllArgsConstructor
@Getter
public enum TageEnum {
    PAY_ORDER(0, "order-order", "支付订单标签"),
    REFUND_ORDER(1, "refund-order", "退款订单标签"),
    MEDICINE_DELAY(2, "medicine_delay", "用药延时提醒");

    private int type;
    private String tag;
    private String describe;

    public static TageEnum check(Integer type) {
        if (null == type) {
            return null;
        }
        for (TageEnum c : TageEnum.values()) {
            if (c.getType() == type) {
                return c;
            }
        }
        throw new RuntimeException("标签不存在");
    }

    public static String[] getArrays(List<Integer> tagList) {
        String[] arrays = null;
        if (null != tagList && !tagList.isEmpty()) {
            int m = tagList.size();
            arrays = new String[m];
            for (int i = 0; i < m; i++) {
                Integer type = tagList.get(i);
                if (type == null) {
                    continue;
                }
                arrays[i] = check(type).getTag();
            }
        }
        return arrays;
    }
}
