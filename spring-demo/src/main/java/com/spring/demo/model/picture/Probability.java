package com.spring.demo.model.picture;

import lombok.Data;
import lombok.ToString;

/**
 * 行置信度信息；如果输入参数 probability = true 则输出
 *
 * @author xuweizhi
 * @since 2019/09/20 17:17
 */
@Data
@ToString
public class Probability {

    /**
     * 行置信度平均值
     */
    private String average;

    /**
     * 行置信度最小值
     */
    private String min;

    /**
     * 行置信度方差
     */
    private String variance;
}
