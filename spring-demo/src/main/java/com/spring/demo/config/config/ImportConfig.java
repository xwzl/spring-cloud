package com.spring.demo.config.config;

import com.spring.demo.config.properties.ImportProperties;
import com.spring.demo.config.properties.MyImportBeanDefinitionRegister;
import com.spring.demo.config.properties.MyImportSelector;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author xuweizhi
 * @since 2019/09/21 13:48
 */
@Configuration
@Import({ImportProperties.class, MyImportSelector.class, MyImportBeanDefinitionRegister.class})
public class ImportConfig {

}
