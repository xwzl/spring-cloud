package com.spring.starter.model;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * younger 属性注入测试
 *
 * @author xuweizhi
 * @since 2019-08-23
 */
@ConfigurationProperties(prefix = "sample.younger")
public class YoungerModel {

    private String username = "";

    private int age = 1;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
