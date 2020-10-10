package com.spring.component.json;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xuweizhi
 */
@Slf4j
@Configuration
public class JsonAutoConfiguration {

    @Bean
    @ConditionalOnClass(name = {"com.fasterxml.jackson.databind.ObjectMapper"})
    public JSON json(ObjectMapper objectMapper) {
        return new JSON(objectMapper);
    }

}
