package com.spring.demo.config.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.apache.commons.lang3.StringUtils;
import org.apache.skywalking.apm.toolkit.trace.TraceContext;

import java.util.Objects;

public class TraceLogConfig extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        String trace = TraceContext.traceId();
        if (StringUtils.isBlank(trace) || Objects.equals("N/A", trace)) {
            return "NA";
        }
        return trace;
    }

}