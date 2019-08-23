package com.spring.starter.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * younger 属性注入测试
 *
 * @author xuweizhi
 * @since 2019-08-23
 */
@Component
public class YoungerModel {

    @Value("${sample.younger.username}")
    private String username;

    @Value("${sample.younger.age}")
    private int age;

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
