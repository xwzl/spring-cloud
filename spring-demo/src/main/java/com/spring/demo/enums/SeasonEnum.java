package com.spring.demo.enums;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

/**
 * @author xuweizhi
 * @since 2019-08-08
 */
public enum SeasonEnum {

    /**
     * 春天
     */
    SPRING(1, "春天"),
    /**
     * 夏天
     */
    SUMMER(2, "夏天"),
    /**
     * 秋天
     */
    AUTUMN(3, "秋天"),
    /**
     * 冬天
     */
    WINTER(4, "冬天");


    private Integer code;
    private String message;

    @Contract(pure = true)
    SeasonEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Contract(pure = true)
    public Integer getCode() {
        return code;
    }

    @Contract(pure = true)
    public String getMessage() {
        return message;
    }

    /**
     * 利用 message 获取枚举吗？
     */
    @Nullable
    public static SeasonEnum getByMessage(String message) {
        for (SeasonEnum seasonEnum : SeasonEnum.values()) {
            if (seasonEnum.getMessage().equals(message)) {
                return seasonEnum;
            }
        }
        return null;
    }

    @Nullable
    public static SeasonEnum getByCode(Integer code) {
        for (SeasonEnum seasonEnum : SeasonEnum.values()) {
            if (seasonEnum.getCode().equals(code)) {
                return seasonEnum;
            }
        }
        return null;
    }


}
