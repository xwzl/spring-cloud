package com.spring.starter.service;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author pengfei.li
 * @date 2018/11/1
 */
@ConfigurationProperties("hello.service")
public class HelloServiceProperties {

    private String prefix;

    private String suffix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
