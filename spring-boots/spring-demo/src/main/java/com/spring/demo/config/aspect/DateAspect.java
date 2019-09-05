package com.spring.demo.config.aspect;

import com.spring.demo.annotation.DateHelper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Controller 方法时间统一处理
 *
 * @author xuweizhi
 * @date 2019/06/13 21:21
 */
@Aspect
@Component
@Order(-1)
@Slf4j
public class DateAspect {

    @Around("@annotation(helper)")
    public Object around(ProceedingJoinPoint pjp, DateHelper helper) throws Throwable {
        long start = System.currentTimeMillis();
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().getSimpleName();
        Object o = pjp.proceed();
        long end = System.currentTimeMillis() - start;
        log.info("{} {} 耗时= {} ms", className, methodName, end);
        return o;
    }
}
