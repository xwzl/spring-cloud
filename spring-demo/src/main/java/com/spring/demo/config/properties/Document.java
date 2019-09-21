package com.spring.demo.config.properties;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author xuweizhi
 * @since 2019/09/21 14:24
 */
@Data
public class Document {

    private String name;

    private String tag;

    private BigDecimal price;
}
