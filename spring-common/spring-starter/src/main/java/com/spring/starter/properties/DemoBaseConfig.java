package com.spring.starter.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 自动注入配置，这样在其子项目注入这个配置文件，不会报错
 *
 * @author xuweizhi
 * @since 2019-08-23
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "six.spring.starter")
public class DemoBaseConfig {

    private ApplePhoneProperties apple = new ApplePhoneProperties();

}

