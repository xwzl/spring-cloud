package com.spring.demo.config;

import com.spring.demo.annotation.MyValidated;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * 专门用于数据校验的接口
 * A: 表示需要校验的注解
 * T: 表示需要校验的是成员变量的数据类型，Object 表示成员变量的类型为任意类型
 *
 * @author xuweizhi
 * @since 2019-05-12 10:05
 */
@Data
@Slf4j
public class MyConstraintValidator implements ConstraintValidator<MyValidated, String> {

    private String message;

    private String value;

    @Override
    public void initialize(MyValidated myValidated) {
        log.info(this.getClass().getName() + " 初始化 ！");
        this.message = myValidated.message();
        this.value = myValidated.value();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value.equals(this.value)) {
            return false;
        }
        return true;
    }
}
