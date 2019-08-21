package com.spring.demo.config.handler;

import com.spring.demo.enums.HttpStatusEnum;
import com.spring.demo.exception.ApiException;
import com.spring.demo.exception.ServiceException;
import com.spring.demo.model.vos.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 全局异常处理
 *
 * @author xuweizhi
 * @since 2019-08-21
 */
@Slf4j
@RestController
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ApiResult<?> handlerException(@NotNull Throwable e) {
        return new ApiResult(HttpStatusEnum.INTERNAL_SERVER_ERROR.value(),
                HttpStatusEnum.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    @ExceptionHandler(ApiException.class)
    public void handleApiException(ApiException e) throws IOException {
        log.error("meet ApiException: " + e.getCode() + " " + e.getMessage());
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        response.sendError(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(ServiceException.class)
    public ApiResult<?> handleServiceException(ServiceException e) {
        log.error("meet ServiceException: " + e.getCode() + " " + e.getMessage(),e);
        return new ApiResult(e);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ApiResult<?> illegalArgumentException(IllegalArgumentException e) {
        log.error("meet ServiceException: " + e.getMessage());
        return new ApiResult<>(-1, e.getMessage());
    }

}
