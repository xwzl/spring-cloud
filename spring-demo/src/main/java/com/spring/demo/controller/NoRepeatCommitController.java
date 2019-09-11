package com.spring.demo.controller;

import com.spring.demo.annotation.CacheLock;
import com.spring.demo.annotation.CacheParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 防止重复提交
 *
 * @author xuweizhi
 * @since 2019/09/11 13:36
 */
@RestController
@RequestMapping("/noCommit")
public class NoRepeatCommitController {

    @CacheLock(prefix = "books")
    @GetMapping
    public String query(@CacheParam(name = "token") @RequestParam String token) {
        return "success - " + token;
    }
}
