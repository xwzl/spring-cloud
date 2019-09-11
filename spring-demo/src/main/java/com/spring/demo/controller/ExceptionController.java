package com.spring.demo.controller;

import com.spring.demo.annotation.AopSample;
import com.spring.demo.enums.ServiceCodeEnum;
import com.spring.demo.exception.ServiceException;
import com.spring.demo.model.vos.ValidatedVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

/**
 * 全局异常处理测试
 *
 * @author xuweizhi
 * @since 2019-08-21
 */
@RestController
@RequestMapping("/exception")
@Api("异常处理和验证测试")
@Slf4j
public class ExceptionController {

    @GetMapping
    public void test1() {
        this.exceptionProducer();
    }

    private void exceptionProducer() {
        if (Math.random() > 0.5) {
            throw new ServiceException(ServiceCodeEnum.VERIFICATION_CODE_ERROR);
        }
    }

    @AopSample
    @PostMapping
    @ApiOperation("验证处理")
    public void validatedDemo(@Validated @RequestBody ValidatedVO validatedVO, BindingResult result) {
        Optional.ofNullable(result).filter(Errors::hasErrors).ifPresent(bindingResult -> {
            throw new ServiceException(0, Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        });
        log.info(validatedVO.toString());
    }

}
