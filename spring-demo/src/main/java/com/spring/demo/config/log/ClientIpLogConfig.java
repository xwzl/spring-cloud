package com.spring.demo.config.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.spring.demo.config.ApplicationRunnerAfter;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class ClientIpLogConfig extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return ApplicationRunnerAfter.ip;
        }
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
        return IpUtil.getIpAddr(request);
    }

}