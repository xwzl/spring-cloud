package com.spring.common.model.common;

import com.spring.common.model.exception.ServiceException;
import com.spring.common.model.utils.ServiceCodeEnum;
import lombok.Data;
import lombok.ToString;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * @author xuweizhi
 * @since 2019-08-21
 */
@Data
@ToString
public class ResultVO<T> {

    private Integer code;

    private String message;

    private T data;


    public ResultVO() {
        this.code = ServiceCodeEnum.SUCCESS.getCode();
        this.message = ServiceCodeEnum.SUCCESS.getMessage();
        this.data = null;
    }

    public ResultVO(T data) {
        this.code = ServiceCodeEnum.SUCCESS.getCode();
        this.message = ServiceCodeEnum.SUCCESS.getMessage();
        this.data = data;
    }

    @Contract(pure = true)
    public ResultVO(int code, String message) {
        this.code = code;
        this.message = message;
        this.data = null;
    }

    @Contract(pure = true)
    public ResultVO(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResultVO(@NotNull ServiceCodeEnum sce) {
        this.code = sce.getCode();
        this.message = sce.getMessage();
        this.data = null;
    }

    @Contract(pure = true)
    public ResultVO(@NotNull ServiceCodeEnum sce, T data) {
        this.code = sce.getCode();
        this.message = sce.getMessage();
        this.data = data;
    }

    @Contract(pure = true)
    public ResultVO(@NotNull ServiceException e) {
        this.code = e.getCode();
        this.message = e.getMessage();
        this.data = null;
    }

    @Contract(pure = true)
    public ResultVO(@NotNull ServiceException e, T data) {
        this.code = e.getCode();
        this.message = e.getMessage();
        this.data = data;
    }

    public static <T> ResultVO<T> failed() {
        ResultVO<T> result = new ResultVO<>();
        result.setCode(ServiceCodeEnum.FAIL.getCode());
        result.setMessage(ServiceCodeEnum.FAIL.getMessage());
        return result;
    }

    public boolean isSucceed() {
        return code == ServiceCodeEnum.SUCCESS.getCode();
    }

    /**
     * success
     *
     * @param data //
     * @param <T>  //
     * @return //
     */
    public static <T> ResultVO<T> success(T data) {
        ResultVO<T> result = new ResultVO<>(data);
        result.setCode(ServiceCodeEnum.SUCCESS.getCode());
        result.setMessage(ServiceCodeEnum.SUCCESS.getMessage());
        return result;
    }
}
