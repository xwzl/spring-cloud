package com.spring.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author xuweizhi
 * @since 2019/08/23 17:56
 */
@Configuration
@ConfigurationProperties(prefix = "customer.my.define")
public class DefineConfig {

    private String config;

    private String indexUrl;

    private String customerClass;

    private int maxLive;

    public String getConfig() {
        return config;
    }

    public void setConfig(String config) {
        this.config = config;
    }

    public String getIndexUrl() {
        return indexUrl;
    }

    public void setIndexUrl(String indexUrl) {
        this.indexUrl = indexUrl;
    }

    public String getCustomerClass() {
        return customerClass;
    }

    public void setCustomerClass(String customerClass) {
        this.customerClass = customerClass;
    }

    public int getMaxLive() {
        return maxLive;
    }

    public void setMaxLive(int maxLive) {
        this.maxLive = maxLive;
    }

    @Override
    public String toString() {
        return "DefineConfig{" +
                "config='" + config + '\'' +
                ", indexUrl='" + indexUrl + '\'' +
                ", customerClass='" + customerClass + '\'' +
                ", maxLive=" + maxLive +
                '}';
    }
}
