package com.spring.demo.service.provider;

import org.apache.ibatis.jdbc.SQL;

/**
 * @author xuweizhi
 * @since 2019/08/05 19:24
 */
public class RoleProvider {


    public String buildGetUsersByName() {
        return new SQL() {{
            SELECT("*");
            FROM("role");
        }}.toString();
    }
}
