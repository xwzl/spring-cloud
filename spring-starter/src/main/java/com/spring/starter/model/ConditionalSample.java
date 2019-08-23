package com.spring.starter.model;

import lombok.Data;

/**
 * conditional 注解专用对象
 *
 * @author xuweizhi
 * @since 2019-08-23
 */
@Data
public class ConditionalSample {

    /**
     * 貌似不用这么麻烦，好像可以通过打印的方式判断 bean 是否注入
     */
    private String hello;

    /**
     * 拜拜
     */
    private String byeBye;
}
