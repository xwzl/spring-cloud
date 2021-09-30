package com.spring.framework.model;

import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.jni.Address;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author xuweizhi
 */
@Data
@Slf4j
@ToString
@Component
public class School {

    private String hello = "hello";

    @Resource
    private Address address;

    public School() {
        log.info("school");
    }

    public School(String hello, Address address) {
        this.hello = hello;
        this.address = address;
        log.info("school test");
    }
}
