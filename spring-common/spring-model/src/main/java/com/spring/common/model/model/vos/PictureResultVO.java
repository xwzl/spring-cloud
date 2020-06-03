package com.spring.common.model.model.vos;

import lombok.Data;
import lombok.ToString;
import org.jetbrains.annotations.Contract;

/**
 * @author xuweizhi
 * @since 2019/09/20 16:49
 */
@Data
@ToString
public class PictureResultVO<T> {

    /**
     * 换行字符串
     */
    private String wrap;

    /**
     * 不换行字符串
     */
    private String noWrap;

    @Contract(pure = true)
    public PictureResultVO() {
    }

    @Contract(pure = true)
    public PictureResultVO(String wrap, String noWrap) {
        this.wrap = wrap;
        this.noWrap = noWrap;
    }
}
