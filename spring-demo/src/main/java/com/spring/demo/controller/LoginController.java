package com.spring.demo.controller;

import lombok.Data;
import org.springframework.web.bind.annotation.*;

/**
 * @author xuweizhi
 * @since 2019/11/01 17:31
 */
@RestController
@RequestMapping("/user")
public class LoginController {

    @PostMapping("/login")
    public void login(@RequestBody User user) {
        System.out.println(user.toString());
    }

    @GetMapping("/index")
    public void test() {
        System.out.println("111");
    }

    @Data
    public static class User {
        private String account;
        private String password;
    }
}
