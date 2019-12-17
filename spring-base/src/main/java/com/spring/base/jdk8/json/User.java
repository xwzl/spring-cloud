package com.spring.base.jdk8.json;

import lombok.Data;

import java.util.List;

/**
 * 测试
 *
 * @author xuweizhi
 * @since 2019/12/10 21:31
 */
@Data
public class User {

    private String username;

    private List<String> tags;

}
