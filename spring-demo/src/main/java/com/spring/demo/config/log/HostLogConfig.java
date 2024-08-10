package com.spring.demo.config.log;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import com.spring.demo.config.ApplicationRunnerAfter;

public class HostLogConfig extends ClassicConverter {

    @Override
    public String convert(ILoggingEvent event) {
        return ApplicationRunnerAfter.ip;
    }

}