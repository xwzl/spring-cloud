package com.spring.demo.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * 用于工作测试代码
 *
 * @author xuweizhi
 * @since 2019/11/19 15:49
 */
@Slf4j
@RestController
@RequestMapping("/work")
@Api(tags = "工作测试代码")
@Validated
public class WorkController {

    /**
     * 参数校验
     *
     * @param hello hello
     * @param work  work
     */
    @GetMapping("/validated")
    @ApiOperation(value = "validated", tags = "参数校验")
    @ApiImplicitParams({@ApiImplicitParam(value = "hello 不能为空", name = "hello", paramType = "query"),
            @ApiImplicitParam(value = "work 不能为空", name = "work", paramType = "query")})
    public void test(@NotBlank(message = "hello 不能为空") String hello, @NotBlank(message = "work 不能为空") String work) {
        log.info(hello, work);
    }

    @GetMapping("pathParam/{id}")
    public void pathParam(@PathVariable("id") String id) {
        log.info(id);
    }
}
