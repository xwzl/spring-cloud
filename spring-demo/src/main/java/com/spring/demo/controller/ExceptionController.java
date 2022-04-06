package com.spring.demo.controller;

import com.spring.common.model.exception.ServiceException;
import com.spring.demo.annotation.AopSample;
import com.spring.demo.config.http.HttpApiService;
import com.spring.demo.model.vos.ValidatedVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * 全局异常处理测试
 *
 * @author xuweizhi
 * @since 2019-08-21
 */
@Slf4j
@RestController
@RequestMapping("/exception")
//@Api(tags = "异常处理和验证测试")
public class ExceptionController {

    @Resource
    private HttpApiService httpApiService;

    @Resource
    private RestTemplate restTemplate;

    @GetMapping
    //@ApiOperation("简单测试")
    private void message() {
        String str = null;
        for (int i = 0; i < 2; i++) {
            try {
                str = httpApiService.doGet("http://www.baidu.com");
            } catch (Throwable e) {
                System.out.println(e.getMessage());
            }
            System.out.println(i + "; " + str);
        }
    }

    @GetMapping("/exception")
    //@ApiOperation("调用本地服务")
    public String test() throws Exception {
        Map<String, Object> params = new HashMap<>();
        params.put("username", "username");
        params.put("password", "username");
        params.put("age", "7");
        params.put("diy", "username");
        params.put("fix", "username");
        String s1 = httpApiService.doGet("http://localhost:11111/validated", params);
        String s = httpApiService.doGet("http://localhost:11111/exception");
        return "xxx";
    }

    @GetMapping("/restTemplate")
    //@ApiOperation("restTemplate 调用本地服务")
    public void restTemplate() {
        restTemplate.getForObject("http://127.0.0.1:11111/rest/get/{noticeId}", String.class, "111", "2222");
    }

    @AopSample
    @PostMapping
    //@ApiOperation("验证处理")
    public void validatedDemo(@Validated @RequestBody ValidatedVO validatedVO, BindingResult result) {
        Optional.ofNullable(result).filter(Errors::hasErrors).ifPresent(bindingResult -> {
            throw new ServiceException(0, Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
        });
        log.info(validatedVO.toString());
    }


}
