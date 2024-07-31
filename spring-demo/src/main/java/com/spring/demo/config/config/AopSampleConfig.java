package com.spring.demo.config.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author xuweizhi
 * @date 2019/09/05 21:55
 */
@Aspect
@Component
@Slf4j
public class AopSampleConfig {

    /**
     * aop 切面 ， 切类或者方法
     */
    @Pointcut(value = "@within(com.spring.demo.annotation.AopSample) || @annotation(com.spring.demo.annotation.AopSample)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before(@NotNull JoinPoint joinPoint) {
        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        //获取类名
        String declaringTypeName = joinPoint.getSignature().getDeclaringTypeName();
        //获取切入方法名
        String methodName = joinPoint.getSignature().getName();
        //获取切入点方法参数类型
        Object[] args = joinPoint.getArgs();
        log.info("请求的类名:" + declaringTypeName + ",方法名:" + methodName + ",入参:" + Arrays.toString(args));
    }

    //@Around("pointcut()")
    //public Object around(ProceedingJoinPoint pjp) {
    //    try {
    //        //调用目标原有的方法
    //        log.info("环绕通知是一个很强大的通知，使用不当，可能会影响程序的进行");
    //        Object o = pjp.proceed();
    //        log.info("方法环绕proceed，结果是 :{}", o);
    //        return o;
    //    } catch (Throwable e) {
    //        e.printStackTrace();
    //        return null;
    //    }
    //}

    @AfterThrowing("pointcut()")
    public void throwsException(JoinPoint jp) {
        log.info("方法异常时执行.....");
    }

    /**
     * 无论如何最终都执行
     */
    @After("pointcut()")
    public void after() {
        log.info("After Advice={}", "This is a after advice!");
    }

    @AfterReturning(pointcut = "pointcut()", returning = "object")
    public void getAfterReturn(Object object) {
        if (!Objects.isNull(object)) {
            log.info("afterReturning={}", object.toString());
        }
    }


}
