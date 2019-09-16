package com.spring.base.thread.disruptor.feeling;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 手机元数据
 *
 * @author xuweizhi
 * @since 2019/09/16 21:19
 */
@Data
@ToString
public class Phone {

    /**
     * 手机编号
     */
    private String id;

    /**
     * 价格
     */
    private double price;

    /**
     * 厂商
     */
    private String brand;

    /**
     * 生产时间
     */
    private LocalDateTime createTime;

    /**
     * 收获地址
     */
    private String shippingAddress;

    /**
     * 机器编号
     */
    private String machineCode;

    /**
     * 保修时间
     */
    private int warrantyTime;

}
