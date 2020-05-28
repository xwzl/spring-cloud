package com.java.prepare.provider;

import com.java.prepare.model.Student;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

/**
 * @author xuweizhi
 * @since 2020/05/25 15:06
 */
public class UserProvider {

    /**
     * 模糊查询
     *
     * @param keyWord
     *            关键字
     * @return sql
     */
    public String search(@Param("keyWord") String keyWord) {
        SQL sql = new SQL();
        sql.SELECT("user_name", "address", "age", "id");
        sql.FROM("user");
        sql.WHERE("user_name like CONCAT('%',#{keyWord},'%')");
        return sql.toString();
    }

    /**
     * 模糊查询
     *
     * @param student
     *            关键字
     * @return sql
     */
    public String searchUser(@Param("user") Student student) {
        SQL sql = new SQL();
        sql.SELECT("user_name", "address", "age", "id");
        sql.FROM("user");
        sql.WHERE("user_name like CONCAT('%',#{user.userName},'%')");
        return sql.toString();
    }
}
