package com.spring.common.model.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

/**
 * 通用的HTTP接口返回对象
 *
 * @author xuweizhi
 * @since 2019/11/5 15:25 星期三
 */
@Data
@Schema(description = "响应结果")
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 响应代码
     */
    @Schema(description = "响应代码")
    private Integer code;

    /**
     * 响应消息
     */
    @Schema(description = "响应消息")
    private String msg;

    /**
     * 响应数据
     */
    @Schema(description = "响应数据")
    private T data;

    /**
     * 操作成功
     *
     * @param data 数据
     * @param <T>  泛型
     * @return Response<T>
     */
    public static <T> Response<T> success(T data) {
        return new Response<>(200, "操作成功", data);
    }

    /**
     * 操作成功
     *
     * @param msg  消息
     * @param data 数据
     * @param <T>  泛型
     * @return Response<T>
     */
    public static <T> Response<T> success(String msg, T data) {
        return new Response<>(200, msg, data);
    }

    /**
     * 操作失败
     *
     * @param <T> 泛型
     * @return Response<T>
     */
    public static <T> Response<T> failed() {
        ErrorInfo error = ErrorInfo.error();
        return new Response<>(error.getCode(), error.getMsg(), null);
    }

    /**
     * 操作失败
     * - 自定义信息
     *
     * @param msg 错误信息
     * @param <T> 泛型
     * @return Response<T>
     */
    public static <T> Response<T> failed(String msg) {
        ErrorInfo error = ErrorInfo.error(msg);
        return new Response<>(error.getCode(), error.getMsg(), null);
    }

    /**
     * 操作失败
     * - 错误对象
     *
     * @param error 错误对象
     * @param <T>   泛型
     * @return Response<T>
     */
    public static <T> Response<T> failed(ErrorInfo error) {
        return new Response<>(error.getCode(), error.getMsg(), null);
    }

    /**
     * 响应结果
     *
     * @param code 代码
     * @param msg  消息
     * @param data 数据
     * @param <T>  泛型
     * @return Response<T>
     */
    public static <T> Response<T> response(int code, String msg, T data) {
        return new Response<>(code, msg, data);
    }

    /**
     * 判断响应结果是否成功
     *
     * @return boolean
     */
    public boolean isSuccess() {
        if (Objects.isNull(code)) {
            return false;
        }
        return Objects.equals(Integer.valueOf("200"), code);
    }
}
