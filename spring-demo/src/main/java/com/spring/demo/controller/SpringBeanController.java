package com.spring.demo.controller;

import com.spring.demo.config.properties.HttpProperties;
import com.spring.demo.config.properties.ImportBeanRegister;
import com.spring.demo.config.properties.ImportProperties;
import com.spring.demo.config.properties.ImportSelector;
import com.spring.demo.untils.SpringContextUtil;
import org.jetbrains.annotations.Contract;
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

    private final ImportBeanRegister importBeanRegister;

    private final ImportSelector importSelector;

    private final ImportProperties importProperties;

    @Contract(pure = true)
    public SpringBeanController(ImportBeanRegister importBeanRegister, ImportSelector importSelector, ImportProperties importProperties) {
        this.importBeanRegister = importBeanRegister;
        this.importSelector = importSelector;
        this.importProperties = importProperties;
    }


    @GetMapping("/bean")
    public void acquireBean() {
        HttpProperties bean = SpringContextUtil.getBean(HttpProperties.class);
        System.out.println(bean);
    }

    @GetMapping("/print")
    public void print() {
        importBeanRegister.say();
        importProperties.say();
        importSelector.say();
    }

}
