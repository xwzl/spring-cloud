package com.spring.demo.controller;

import org.springframework.web.bind.annotation.*;

/**
 * 不同的请求方式，带参数的形式不同
 *
 * @author xuweizhi
 * @since 2019-08-09
 */
@RestController
@RequestMapping("/post")
public class PostParamController {

    /**
     * url ？ 后面的参数 @RequestParam
     */
    @PostMapping("/test1")
    public void test1(@RequestParam String param1) {
        System.out.println(param1);
    }

    /**
     * request body 中的参数
     */
    @PostMapping("/test2")
    public void test2(@RequestBody String param2) {
        System.out.println(param2);
    }

    @PostMapping("/test3")
    public void test3(String param2) {
        System.out.println(param2);
    }

    /**
     * url ？ 后面的参数 @RequestParam
     */
    @GetMapping("/test4")
    public void test4(String param4) {
        System.out.println(param4);
    }

    @GetMapping("/test5/{id}")
    public void test5(@PathVariable("id") String id) {
        System.out.println(id);
    }

    @GetMapping("/test6")
    public void test6(@RequestParam("param6") String param6) {
        System.out.println(param6);
    }
}
