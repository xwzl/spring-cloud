package com.spring.base;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * 枚举 switch 测试
 *
 * @author xuweizhi
 * @since 2019/09/14 18:21
 */
@Getter
public enum NettyEnum {

    /**
     * 飞机
     */
    AIRPLANE(1, "飞机"),

    /**
     * 大炮
     */
    CANNON(2, "大炮");

    private int code;

    private String message;

    @Contract(pure = true)
    NettyEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    @NotNull
    public static NettyEnum getValue(int code) {
        for (NettyEnum nettyEnum : NettyEnum.values()) {
            if (nettyEnum.getCode() == code) {
                return nettyEnum;
            }
        }
        throw new RuntimeException("状态错误！");
    }
}

@Slf4j
class SwitchDemo {
    public static void main(String[] args) {
        switch (Objects.requireNonNull(NettyEnum.getValue(1))) {
            case AIRPLANE:
                log.info("飞机");
                break;
            case CANNON:
                log.info("大炮");
                break;
            default:
                log.info("错误");
        }
    }
}

