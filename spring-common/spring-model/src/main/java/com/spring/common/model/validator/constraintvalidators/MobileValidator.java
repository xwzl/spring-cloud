package com.spring.common.model.validator.constraintvalidators;

import com.spring.common.model.validator.constraints.Mobile;
import org.apache.logging.log4j.util.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * 手机号校验
 *
 * @author xuweizhi
 * @since 2019/11/29
 */
public class MobileValidator implements ConstraintValidator<Mobile, String> {

    private static final Pattern MOBILE_PATTERN = Pattern.compile("1\\d{10}");

    /**
     * 校验手机号码
     *
     * @param mobile 手机号码
     * @return boolean
     */
    public static boolean isMobile(String mobile) {
        return MOBILE_PATTERN.matcher(mobile).matches();
    }

    @Override
    public void initialize(Mobile constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Strings.isBlank(value) || isMobile(value);
    }
}
