package com.spring.component.api.version;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * @author xuweizhi
 */
@Slf4j
public class VersionRequestMappingHandlerMapping extends RequestMappingHandlerMapping {

    @Override
    protected RequestCondition<?> getCustomTypeCondition(Class<?> handlerType) {
        VersionedController versionedController =
                AnnotationUtils.findAnnotation(handlerType, VersionedController.class);
        RequestCondition<?> condition = createCondition(versionedController);
        if (condition != null && log.isDebugEnabled()) {
            log.debug("created versioned condition for class：" + handlerType.getName());
        }
        return condition;
    }

    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        VersionedController versionedController = AnnotationUtils.findAnnotation(method, VersionedController.class);
        if (versionedController == null) {
            versionedController = AnnotationUtils.findAnnotation(method.getDeclaringClass(), VersionedController.class);
        }
        RequestCondition<?> condition = createCondition(versionedController);
        if (condition != null && log.isDebugEnabled()) {
            log.debug("created versioned condition for method：" + method.getName());
        }
        return condition;
    }

    @Override
    protected boolean isHandler(Class<?> beanType) {
        return super.isHandler(beanType) && beanType.getAnnotation(FeignClient.class) == null;
    }

    private RequestCondition<VersionCondition> createCondition(VersionedController versionedController) {
        return versionedController == null ? null : new VersionCondition(versionedController.value());
    }

}
