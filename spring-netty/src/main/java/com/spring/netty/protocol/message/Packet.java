package com.spring.netty.protocol.message;

import lombok.Data;

/**
 * 包
 *
 * @author xuweizhi
 * @since 2019-08-22
 */
@Data
public abstract class Packet {

    /**
     * 版本
     */
    private Byte version = 1;

    /**
     * 获取版本号
     *
     * @return
     */
    public abstract Byte getCommand();
}
