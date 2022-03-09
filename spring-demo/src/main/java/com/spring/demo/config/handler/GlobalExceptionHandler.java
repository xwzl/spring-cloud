package com.spring.demo.config.handler;

import com.spring.common.model.exception.ApiException;
import com.spring.common.model.exception.ServiceException;
import com.spring.common.model.common.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    public ResultVO<?> handlerException(Throwable e) {
        log.error(ExceptionUtils.getStackTrace(e));
        return new ResultVO(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
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
    public ResultVO<?> handleServiceException(ServiceException e) {
        log.error("meet ServiceException: " + e.getCode() + " " + e.getMessage(), e);
        return new ResultVO(e);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResultVO<?> illegalArgumentException(IllegalArgumentException e) {
        log.error("meet ServiceException: " + e.getMessage());
        return new ResultVO<>(-1, e.getMessage());
    }

    /**
     * 方法参数校验
     */
    @ExceptionHandler(BindException.class)
    public ResultVO<?> handleMethodArgumentNotValidException(BindException e) {
        log.error(e.getMessage(), e);
        return new ResultVO<>(HttpStatus.BAD_REQUEST.value(), e.getBindingResult().getFieldError().getDefaultMessage());
    }

    /**
     * ValidationException
     * <p>
     * Collections.EMPTY_LIST返回的是一个空的List。为什么需要空的List呢？ 有时候我们在函数中需要返回一个List，但是这个List是空的，如果我们直接返回null的话，
     * 调用者还需要进行null的判断，所以一般建议返回一个空的List。Collections.EMPTY_LIST返回的这个空的List是不能进行添加元素这类操作的。这时候你有可能会说，我
     * 直接返回一个new ArrayList()呗，但是new ArrayList()在初始化时会占用一定的资源，所以在这种场景下，还是建议返回Collections.EMPTY_LIST。
     * <p>
     * Collections. emptyList()返回的也是一个空的List，它与Collections.EMPTY_LIST的唯一区别是，Collections. emptyList()支持泛型，所以在需要泛型的时候，
     * 可以使用Collections. emptyList()。
     * <p>
     * Collections.EMPTY_MAP和Collections.EMPTY_SET同理。
     */
    @ExceptionHandler(ValidationException.class)
    public ResultVO<?> handleValidationException(ValidationException e) {
        log.error(e.getMessage(), e);
        return new ResultVO<>(HttpStatus.BAD_REQUEST.value(), appendException(new ArrayList<>(), e.getMessage()).toString());
    }

    //@ExceptionHandler(NoHandlerFoundException.class)
    //public ApiResult<?> handlerNoFoundException(Exception e) {
    //    log.error(e.getMessage(), e);
    //    return new ApiResult<>(404, "路径不存在，请检查路径是否正确");
    //}

    /**
     * 解析报错异常
     *
     * @param result  返回 list
     * @param message 异常信息
     * @return 返回值
     */
    public List<String> appendException(List<String> result, String message) {
        String[] splits = message.split(",");
        for (String value : splits) {
            String trim = value.substring(value.indexOf(".") + 1).trim();
            result.add(trim);
        }
        return result;
    }
}

