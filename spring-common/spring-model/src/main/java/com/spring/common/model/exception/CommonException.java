package com.spring.common.model.exception;


import com.spring.common.model.common.ErrorInfo;

/**
 * 通用异常
 *
 * @author xuweizhi
 * @since 2019/11/29
 */
public class CommonException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    /**
     * 错误信息
     */
    private ErrorInfo error;

    public CommonException(ErrorInfo error) {
        super(error.getMsg());
        this.error = error;
    }

    public CommonException(ErrorInfo error, Throwable cause) {
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
