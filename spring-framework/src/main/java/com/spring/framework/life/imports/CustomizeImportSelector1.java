package com.spring.framework.life.imports;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotationMetadata;
@Slf4j
@Order(102)
@SuppressWarnings("all")
public class CustomizeImportSelector1 implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        log.info("selectImports : " + this.getClass().getSimpleName());
        return new String[]{CustomizeServiceImpl1.class.getName()};
    }
}
