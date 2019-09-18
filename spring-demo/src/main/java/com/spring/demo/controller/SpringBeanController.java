package com.spring.demo.controller;

import com.spring.demo.config.properties.HttpProperties;
import com.spring.demo.untils.SpringContextUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 获取 Spring 中 bean 的信息
 *
 * @author xuweizhi
 * @since 2019/09/18 8:51
 */
@RestController
@RequestMapping("/spring")
public class SpringBeanController {

    @GetMapping("/bean")
    public void acquireBean(){
        HttpProperties bean = SpringContextUtil.getBean(HttpProperties.class);
        System.out.println(bean);
    }

}
