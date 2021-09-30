package com.spring.framework.life.annotations;

import com.spring.framework.life.imports.CustomizeImportSelector1;
import com.spring.framework.life.imports.CustomizeImportSelector2;
import com.spring.framework.life.imports.CustomizeImportSelector3;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.context.annotation.Import;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 *
 * @author xuweizhi
 * @since 2021/09/29 18:49
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage
@Import({CustomizeImportSelector1.class, CustomizeImportSelector2.class, CustomizeImportSelector3.class})
public @interface EnableAutoImportSelector {
}
