package com.spring.demo.config.config;

import com.baomidou.mybatisplus.autoconfigure.ConfigurationCustomizer;
import com.hit.common.mybatis.factory.MapWrapperFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gitlab
 */
@Configuration
public class MybatisConfig {

    @Bean
    public ConfigurationCustomizer mybatisConfigurationCustomizer() {
        return configuration -> configuration.setObjectWrapperFactory(new MapWrapperFactory());
    }

}