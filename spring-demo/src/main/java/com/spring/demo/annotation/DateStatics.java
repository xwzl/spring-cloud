package com.spring.demo.annotation;

import java.lang.annotation.*;

/**
 * controller 时间统计
 *
 * @author xuweizhi
 * @date 2019/06/13 21:22
 */
@Documented
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface DateStatics {

    String value() default "";

}
