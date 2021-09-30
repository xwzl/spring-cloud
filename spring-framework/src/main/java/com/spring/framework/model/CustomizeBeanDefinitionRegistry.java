package com.spring.framework.model;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 注册类
 *
 * @author xuweizhi
 * @since 2021/09/29 17:10
 */
@Data
@Slf4j
public class CustomizeBeanDefinitionRegistry {

    public CustomizeBeanDefinitionRegistry() {
        log.info(this.getClass().getName() + "初始化--------------");
    }
}
