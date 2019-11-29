package com.spring.common.model.exception;

import com.spring.common.model.common.ErrorInfo;

/**
 * 业务异常
 *
 * @author xuweizhi
 * @since 2019/11/29
 */
public class ServiceException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 错误信息
     */
    private ErrorInfo error;

    public ServiceException(ErrorInfo error) {
        super(error.getMsg());
        this.error = error;
    }

    public ServiceException(ErrorInfo error, Throwable cause) {
        super(error.getMsg(), cause);
        this.error = error;
    }

    /**
     * 获取错误信息
     *
     * @return ErrorInfo
     */
    public ErrorInfo getError() {
        return error;
    }
}
