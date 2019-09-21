package com.spring.demo.controller;

import com.spring.demo.annotation.CacheLock;
import com.spring.demo.annotation.CacheParam;
import com.spring.demo.config.config.LockMethodConfig;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 防止重复提交 {@link LockMethodConfig}
 *
 * @author xuweizhi
 * @since 2019/09/11 13:36
 */
@RestController
@RequestMapping("/noCommit")
@ApiOperation("重复提交测试")
public class NoRepeatCommitController {

    @GetMapping
    @CacheLock(prefix = "books")
    @ApiOperation("防重复提价测试")
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