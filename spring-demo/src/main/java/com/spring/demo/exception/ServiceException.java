package com.spring.demo.exception;

import com.spring.demo.enums.ServiceCodeEnum;
import lombok.Getter;

/**
 * @author xuweizhi
 * @since 2019-08-21
 */
@Getter
public class ServiceException extends RuntimeException {

    private Integer code = ServiceCodeEnum.FAIL.getCode();

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {

        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ServiceException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ServiceException(Integer code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public ServiceException(ServiceCodeEnum hs) {
        super(hs.getMessage());
        this.code = hs.getCode();
    }

    public ServiceException(ServiceCodeEnum hs, Throwable e) {
        super(hs.getMessage(), e);
        this.code = hs.getCode();
    }


}
