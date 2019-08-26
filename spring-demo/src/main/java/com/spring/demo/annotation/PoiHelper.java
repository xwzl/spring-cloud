package com.spring.demo.annotation;

import java.lang.annotation.*;

/**
 * @author xuweizhi
 * @since  2019/03/13 9:27
 */

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(value= ElementType.FIELD)
public @interface PoiHelper {

    String value() default "";

    int type() ;

}

