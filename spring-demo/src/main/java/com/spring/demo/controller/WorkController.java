package com.spring.demo.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用于工作测试代码
 *
 * @author xuweizhi
 * @since 2019/11/19 15:49
 */
@Slf4j
@RestController
@RequestMapping("/work")
@Tag(name = "工作测试代码")
@Validated
public class WorkController {

    /**
     * 参数校验
     *
     * @param hello hello
     * @param work  work
     */
    @GetMapping("/validated")
    @Operation(summary  ="validated", tags = "参数校验")
//    @Parameters({@Parameter(name = "hello 不能为空"), @Parameter(name = "work 不能为空")})
    public void test(@NotBlank(message = "hello 不能为空") String hello, @NotBlank(message = "work 不能为空") String work) {
        log.info(hello, work);
    }

    @GetMapping("pathParam/{id}")
    public void pathParam(@PathVariable("id") String id) {
        log.info(id);
    }
}
