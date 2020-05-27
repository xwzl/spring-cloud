package com.spring.common.model.utils;

import com.spring.common.model.exception.ServiceException;

/**
 * 断言工具类
 *
 * @author xuweizhi
 * @since 2020/05/27 13:37
 */
public class Assert {

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new ServiceException(ServiceCodeEnum.FAIL.getCode(), message);
        }
    }

    public static void isNotNull(Object object, String message) {
        if (object != null) {
            throw new ServiceException(ServiceCodeEnum.FAIL.getCode(), message);
        }
    }

    public static void isTrue(boolean express, String message) {
        if (!express) {
            throw new ServiceException(ServiceCodeEnum.FAIL.getCode(), message);
        }
    }
}
