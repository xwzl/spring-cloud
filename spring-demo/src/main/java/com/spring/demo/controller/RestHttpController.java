package com.spring.demo.controller;

import com.spring.demo.model.vos.DataVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest 请求
 *
 * @author xuweizhi
 * @since 2019/09/17 22:33
 */
@RestController
@RequestMapping("/rest")
public class RestHttpController {

    /**
     * 无参请求
     */
    @GetMapping("/get")
    public String noParam() {
        System.out.println("请求成功");
        return "success";
    }

    /**
     * 单个参数请求
     */
    @GetMapping("/get/param")
    public String param(String param) {
        return param;
    }

    /**
     * 返回参数实体
     */
    @GetMapping("/get/entity/{noticeId}")
    public DataVO pathEntity(@PathVariable("noticeId") Integer noticeId, String noticeTitle) {
        return DataVO.builder().noticeId(noticeId).noticeTitle(noticeTitle).build();
    }
}
