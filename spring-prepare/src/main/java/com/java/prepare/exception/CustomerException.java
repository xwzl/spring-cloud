package com.java.prepare.exception;

/**
 * 异常
 *
 * @author xuweizhi
 * @since 2020/05/08 22:54
 */

public class CustomerException extends Exception {


    private String message;

    private int code;

    public CustomerException(String message, int code) {
        this.message = message;
        this.code = code;
    }

    public CustomerException(String message, String message1, int code) {
        super(message);
        this.message = message1;
        this.code = code;
    }

    public CustomerException(String message, Throwable cause, String message1, int code) {
        super(message, cause);
        this.message = message1;
        this.code = code;
    }

    public CustomerException(Throwable cause, String message, int code) {
        super(cause);
        this.message = message;
        this.code = code;
    }

    public CustomerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace,
        String message1, int code) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.message = message1;
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
