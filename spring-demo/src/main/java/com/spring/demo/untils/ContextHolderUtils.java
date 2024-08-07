package com.spring.demo.untils;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

import java.util.Objects;

/**
 * Httpt 工具类
 *
 * @author xuweizhi
 * @since 2019-09-23
 */
public class ContextHolderUtils {

    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();

    }

    public static HttpSession getSession() {
        return getRequest().getSession();
    }

    public static HttpServletRequest getHttpServletRequest() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
    }

    public static HttpServletResponse getHttpServletResponse() {
        return ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
    }

    public static HttpServletResponse getServletWebRequest() {
        return ((ServletWebRequest) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();
    }

    public static ServletContext getServletContext() {
        return Objects.requireNonNull(ContextLoader.getCurrentWebApplicationContext()).getServletContext();
    }
}