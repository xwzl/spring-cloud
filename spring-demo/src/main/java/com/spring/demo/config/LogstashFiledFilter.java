package com.spring.demo.config;

import jakarta.servlet.*;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * logstash 新增字段
 *
 * @author xuweizhi
 */
@Component
public class LogstashFiledFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        MDC.put("tid", TraceContext.traceId());
        MDC.put("host", ApplicationRunnerAfter.ip);

        try {
            chain.doFilter(request, response);
        } finally {
            MDC.remove("tid");
            MDC.remove("host");

        }
    }

    @Override
    public void destroy() {
    }

}