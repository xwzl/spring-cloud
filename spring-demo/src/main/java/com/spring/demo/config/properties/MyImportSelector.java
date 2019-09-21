package com.spring.demo.config.properties;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author xuweizhi
 * @since 2019/09/21 13:57
 */
public class MyImportSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.spring.demo.config.properties.ImportSelector"};
    }

}

