package com.spring.common.model.validator.constraints;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 权限注解
 *
 * @author xuweizhi
 * @since 2019/12/5 15:25 星期三
 */
@Target({TYPE, METHOD, FIELD, PARAMETER})
@Retention(RUNTIME)
@Inherited
@Documented
public @interface RequiredPermission {
    String value() default "";
}
