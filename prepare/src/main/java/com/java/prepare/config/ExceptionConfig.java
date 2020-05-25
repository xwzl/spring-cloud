package com.java.prepare.config;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.spring.common.model.common.ApiResult;
import com.spring.common.model.exception.ServiceException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author xuweizhi
 * @since 2020/05/25 21:54
 */
@Slf4j
@RestController
@RestControllerAdvice
public class ExceptionConfig {

    /**
     * 处理全局异常
     *
     * @param e 服务类异常
     * @return 异常信息
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<String> serviceException(Exception e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return new ApiResult<>(500, "服务器内部异常");
    }

    /**
     * 处理全局异常
     *
     * @param e 服务类异常
     * @return 异常信息
     */
    @ExceptionHandler({ServiceException.class})
    @ResponseStatus(HttpStatus.OK)
    public ApiResult<String> serviceException(ServiceException e) {
        log.error(ExceptionUtil.getMessage(e));
        return new ApiResult<>(e.getCode(), e.getMessage());
    }
}
