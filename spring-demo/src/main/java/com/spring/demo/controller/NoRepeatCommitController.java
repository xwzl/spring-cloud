package com.spring.demo.controller;

import com.spring.demo.annotation.CacheLock;
import com.spring.demo.annotation.CacheParam;
import com.spring.demo.config.config.NoRepeatConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 防止重复提交 {@link NoRepeatConfig}
 *
 * @author xuweizhi
 * @since 2019/09/11 13:36
 */
@RestController
@RequestMapping("/noCommit")
@Api(tags = "重复提交测试")
public class NoRepeatCommitController {

    @RequestMapping
    @CacheLock(prefix = "books")
    @ApiOperation("防重复提交测试")
    public String query(@CacheParam(name = "token") @RequestParam String token) {
        return "success - " + token;
    }

    //public static void main(String[] args) throws NoSuchMethodException, InterruptedException {
    //    Class<NoRepeatCommitController> clazz = NoRepeatCommitController.class;
    //    Method query = clazz.getMethod("query", String.class);
    //    Annotation[][] parameterAnnotations = query.getParameterAnnotations();
    //    for (Annotation[] parameterAnnotation : parameterAnnotations) {
    //        for (Annotation annotation : parameterAnnotation) {
    //            if (annotation instanceof CacheParam) {
    //                System.out.println(((CacheParam) annotation).name());
    //            }
    //        }
    //    }
    //    Parameter[] parameters = query.getParameters();
    //    for (Parameter parameter : parameters) {
    //        CacheParam cacheParam = parameter.getAnnotation(CacheParam.class);
    //        if (cacheParam != null) {
    //            System.out.println(cacheParam.name());
    //        }
    //    }
    //}
}