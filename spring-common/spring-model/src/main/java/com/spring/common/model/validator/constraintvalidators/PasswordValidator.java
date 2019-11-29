package com.spring.common.model.validator.constraintvalidators;

import com.spring.common.model.validator.constraints.Password;
import org.apache.logging.log4j.util.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 密码校验
 *
 * @author xuweizhi
 * @since 2019/11/29
 */
public class PasswordValidator implements ConstraintValidator<Password, String> {

    private static final Pattern PASSWORD_PATTERN = Pattern.compile("(?![0-9]+$)(?![a-zA-Z]+$)(?!(![0-9A-Za-z])+$)\\S{6,32}");

    /**
     * 校验密码
     *
     * @param password 密码
     * @return 通过返回true，不通过返回false
     */
    public static boolean isPassword(String password) {
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    @Override
    public void initialize(Password constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Strings.isBlank(value) || isPassword(value);
    }
}
