package com.spring.demo.pojo;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * @author xuweizhi
 * @since 2019-08-07
 */
@Slf4j
public class DemoException {

    public Integer testOne() {
        Integer ix = 0;
        try {
            int i = 1 / 0;
            ix = 1;
        } catch (Exception e) {
            log.error(ExceptionUtils.getStackTrace(e));
            throw new RuntimeException("1111");
        }
        return ix;
    }
}
