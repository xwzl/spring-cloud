package com.spring.demo.annotation;

import com.spring.demo.validated.MyConstraintValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义格式校验器
 * <p>
 * Constraint 注解用于格式校验，validatedBy 校验逻辑的执行类 MyConstraintValidator
 *
 * @author xuweizhi
 * @since 2019/05/12 10:00
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MyConstraintValidator.class)
public @interface MyValidated {

    /**
     * 过滤指定字符或者字符串的字段信息
     */
    String value();

    /**
     * 错误信息
     */
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
