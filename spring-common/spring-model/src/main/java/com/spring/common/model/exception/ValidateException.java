package com.spring.common.model.exception;

import com.spring.common.model.common.ErrorInfo;

/**
 * 验证异常
 *
 * @author xuweizhi
 * @since 2019/11/29
 */
public class ValidateException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 错误
     */
    private ErrorInfo error;

    public ValidateException() {
        super(ErrorInfo.error().getMsg());
        this.error = ErrorInfo.error();
    }

    public ValidateException(ErrorInfo error) {
        super(error.getMsg());
        this.error = error;
    }

    public ValidateException(ErrorInfo error, Throwable cause) {
        super(error.getMsg(), cause);
        this.error = error;
    }

    /**
     * 获取错误
     *
     * @return 错误
     */
    public ErrorInfo getError() {
        return error;
    }
}
