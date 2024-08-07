package com.java.elastic.aspect;

import com.spring.common.model.utils.GsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.util.Objects;

/**
 * 日志AOP切面
 *
 * @author xuweizhi
 * @since 2019-11-29
 */
@Slf4j
@Aspect
@Component
public class AppLogAspect {
    /**
     * 定义切入点
     */
    @Pointcut("execution(* com.java.elastic.controller.*.*(..))")
    private void appLog() {

    }

    /**
     * 前置通知: 在连接点之前执行的通知
     *
     * @param joinPoint join point
     * @throws Throwable Throwable
     */
    @Before("appLog()")
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        // 接收到请求
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (Objects.isNull(attributes)) {
            return;
        }
        HttpServletRequest request = attributes.getRequest();
        // 请求内容
        log.info("Request Server: {}:{}", InetAddress.getLocalHost().getHostAddress(), request.getServerPort());
        log.info("Url: {}", request.getRequestURL().toString());
        log.info("HTTP Method: {}", request.getMethod());
        String ip = request.getHeader("x-forwarded-for");
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        log.info("IP: {}", ip);
        log.info("Class Method: {}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        // log.info("Args: {}", GsonUtils.toJson(joinPoint.getArgs()));
    }

    /**
     * 后置通知
     *
     * @param rs result
     * @throws Throwable Throwable
     */
    @AfterReturning(returning = "rs", pointcut = "appLog()")
    public void doAfterReturning(Object rs) throws Throwable {
        log.info("Response: {}", GsonUtils.toJson(rs));
    }
}
