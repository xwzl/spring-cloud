package com.spring.common.model.validator.constraintvalidators;

import com.spring.common.model.validator.constraints.Ip;
import org.apache.logging.log4j.util.Strings;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * IP校验
 *
 * @author xuweizhi
 * @since 2019/11/29
 */
public class IpValidator implements ConstraintValidator<Ip, String> {

    private static final Pattern IP_PATTERN = Pattern.compile("([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}");

    /**
     * 校验是否为Ip地址
     *
     * @param ip IP
     * @return boolean
     */
    public static boolean isIp(String ip) {
        return IP_PATTERN.matcher(ip).matches();
    }

    @Override
    public void initialize(Ip constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return Strings.isBlank(value) || isIp(value);
    }
}
