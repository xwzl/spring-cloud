package com.spring.framework.life.imports;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

@SuppressWarnings("all")
public class CustomizeImportSelector3 implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        System.out.println("selectImports : " + this.getClass().getSimpleName());
        return new String[]{"com.spring.framework.life.imports.CustomizeServiceImpl3"};
    }
}
