package com.spring.demo.exception;

import com.spring.demo.enums.HttpStatusEnum;
import lombok.Getter;

/**
 * ApiException 异常
 *
 * @author xuweizhi
 * @since 2019-08-21
 */
@Getter
public class ApiException extends RuntimeException {

    private Integer code = HttpStatusEnum.OK.value();

    public ApiException() {
        super();
    }

    public ApiException(String message) {
        super(message);
    }

    public ApiException(String message, Throwable cause) {
        super(message, cause);
    }

    public ApiException(Throwable cause) {
        super(cause);
    }

    public ApiException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public ApiException(Integer code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public ApiException(Integer code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public ApiException(HttpStatusEnum hs) {
        super(hs.getReasonPhrase());
        this.code = hs.value();
    }

    public ApiException(HttpStatusEnum hs, Throwable e) {
        super(hs.getReasonPhrase(), e);
        this.code = hs.value();
    }
}
