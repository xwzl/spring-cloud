package com.spring.starter.config;

import com.spring.starter.model.ConditionalSample;
import com.spring.starter.model.YoungerModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 自动注入 BaseProperties 对象
 *
 * @author xuweizhi
 * @since 2019-08-23
 */
@Configuration
@EnableConfigurationProperties({YoungerModel.class})
@Slf4j
public class StarterAutoConfig {

    /**
     * 当配置项前缀 sample.conditional ，属性  storeType；
     * 当 com.security.oauth2.storeType=jwt是配置才会生效，matchIfMissing 配置文件中不写这个属性，这个配置会失效
     */
    @Bean
    @ConditionalOnProperty(prefix = "sample.conditional", name = "hello", havingValue = "张杰", matchIfMissing = true)
    @ConfigurationProperties(prefix = "sample.conditional")
    public ConditionalSample conditionalSample() {
        log.info("xxxx");
        return new ConditionalSample();
    }

}
