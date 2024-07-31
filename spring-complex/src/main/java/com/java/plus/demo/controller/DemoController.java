package com.java.plus.demo.controller;

import com.spring.starter.config.DefineConfig;
import com.spring.starter.model.ConditionalSample;
import com.spring.starter.model.YoungerModel;
import com.spring.starter.properties.ApplePhoneProperties;
import com.spring.starter.properties.DemoBaseConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

/**
 * @author xuweizhi
 * @since 2019/08/23 14:47
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    //@Autowired
    //private ConditionalSample conditionalSample;

    @Resource
    private DemoBaseConfig demoBaseConfig;

    @Resource
    private ConditionalSample conditionalSample;

    @Resource
    private DefineConfig defineConfig;

    @Resource
    private YoungerModel youngerModel;

    @GetMapping
    public ApplePhoneProperties demoBaseConfig() {
        return demoBaseConfig.getApple();
    }

    @GetMapping("/conditional")
    public ConditionalSample conditionalSample() {
        return conditionalSample;
    }

    @GetMapping("/defineConfig")
    public String defineConfig() {
        return defineConfig.getConfig();
    }

    @GetMapping("/youngerModel")
    public YoungerModel youngerModel() {
        return youngerModel;
    }
}
