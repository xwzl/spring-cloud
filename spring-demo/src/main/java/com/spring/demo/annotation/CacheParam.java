package com.spring.demo.annotation;

import java.lang.annotation.*;

/**
 * 锁的参数
 *
 * @author xuweizhi
 */
@Target({ElementType.PARAMETER, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CacheParam {

    /**
     * 字段名称
     *
     * @return String
     */
    String name() default "";
}
