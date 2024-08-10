package com.spring.demo.config;

import com.spring.starter.config.IpConfiguration;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

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

    public static String ip = "127.0.0.1";

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("Swagger github : http://" + ipConfiguration.getHostAddress() + ":" + ipConfiguration.getPort() + "/swagger-ui.html");
        log.info("Swagger github boot-strap: http://" + ipConfiguration.getHostAddress() + ":" + ipConfiguration.getPort() + "/doc.html");
        ip = ipConfiguration.getHostAddress();
    }
}
