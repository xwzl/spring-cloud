package com.spring.framework.life.imports;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
@Slf4j
@SuppressWarnings("all")
public class CustomizeImportSelector3 implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        log.info("selectImports : " + this.getClass().getSimpleName());
        return new String[]{CustomizeServiceImpl3.class.getName()};
    }
}
