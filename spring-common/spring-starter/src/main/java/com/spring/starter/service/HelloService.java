package com.spring.starter.service;

/**
 * @author pengfei.li
 * @since 2019/11/1
 */
public class HelloService {

    private String prefix;

    private String suffix;

    public HelloService(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }

    public String say(String text) {
        return String.format("%s , hi , %s , %s", prefix, text, suffix);
    }

}
