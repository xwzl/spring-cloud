package com.spring.demo.model.vos;

import com.spring.demo.enums.ServiceCodeEnum;
import com.spring.demo.exception.ServiceException;
import lombok.Data;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author xuweizhi
 * @since 2019-08-21
 */
@Data
public class ApiResult<T> {

    private Integer code;

    private String message;

    private T data;


    public ApiResult() {
        this.code = ServiceCodeEnum.SUCCESS.getCode();
        this.message = ServiceCodeEnum.SUCCESS.getMessage();
        this.data = null;
    }

    public ApiResult(T data) {
        this.code = ServiceCodeEnum.SUCCESS.getCode();
        this.message = ServiceCodeEnum.SUCCESS.getMessage();
        this.data = data;
    }

    @Contract(pure = true)
    public ApiResult(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    @Contract(pure = true)
    public ApiResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResult(@NotNull ServiceCodeEnum sce) {
        this.code = sce.getCode();
        this.message = sce.getMessage();
        this.data = null;
    }

    @Contract(pure = true)
    public ApiResult(@NotNull ServiceCodeEnum sce, T data) {
        this.code = sce.getCode();
        this.message = sce.getMessage();
        this.data = data;
    }

    @Contract(pure = true)
    public ApiResult(@NotNull ServiceException e) {
        this.code = e.getCode();
        this.message = e.getMessage();
        this.data = null;
    }

    @Contract(pure = true)
    public ApiResult(@NotNull ServiceException e, T data) {
        this.code = e.getCode();
        this.message = e.getMessage();
        this.data = data;
    }

    public boolean isSucceed() {
        return code == ServiceCodeEnum.SUCCESS.getCode();
    }

}
