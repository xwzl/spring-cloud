package com.spring.demo.handler;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xuweizhi
 * @since 2019/08/10 13:49
 */
@RestControllerAdvice
public class GlobalExceptionProcess {


    @ExceptionHandler(RuntimeException.class)
    public Map<String, Object> processException(RuntimeException e) {
        Map<String, Object> result = new HashMap<>();
        result.put("exception", e.getMessage());
        return result;
    }
}
