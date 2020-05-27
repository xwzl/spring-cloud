package com.spring.demo.config;

import com.spring.starter.config.IpConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * SpringBoot 完成后打印 swagger 文档地址
 *
 * @author xuweizhi
 * @date 2019/09/25 22:44
 */
@Slf4j
@Order(100)
@Component
public class ApplicationRunnerAfter implements ApplicationRunner {

    @Resource
    private IpConfiguration ipConfiguration;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Swagger github : http://" + ipConfiguration.getHostAddress() + ":" + ipConfiguration.getPort() + "/swagger-ui.html");
        log.info("Swagger github boot-strap: http://" + ipConfiguration.getHostAddress() + ":" + ipConfiguration.getPort() + "/doc.html");
    }
}
