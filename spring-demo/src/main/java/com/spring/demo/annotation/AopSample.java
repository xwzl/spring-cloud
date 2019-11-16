package com.spring.demo.annotation;

import java.lang.annotation.*;

/**
 * aop 注解测试
 *
 * @author xuweizhi
 * @date 2019/09/05 21:50
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AopSample {

}
