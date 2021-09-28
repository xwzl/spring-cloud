package com.spring.framework.life.imports;

import org.springframework.context.annotation.DeferredImportSelector;
import org.springframework.core.annotation.Order;
import org.springframework.core.type.AnnotationMetadata;

@Order(102)
@SuppressWarnings("all")
public class CustomizeImportSelector1 implements DeferredImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        System.out.println("selectImports : " + this.getClass().getSimpleName());
        return new String[]{"com.spring.framework.life.imports.CustomizeServiceImpl1"};
    }
}
