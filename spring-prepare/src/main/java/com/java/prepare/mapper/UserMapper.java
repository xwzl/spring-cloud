package com.java.prepare.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.java.prepare.model.Student;
import com.java.prepare.provider.UserProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * User mapper
 *
 * @author xuweizhi
 * @since 2020-05-25
 */
public interface UserMapper extends BaseMapper<Student> {

    @SelectProvider(type = UserProvider.class, method = "search")
    List<Student> search(@Param("keyWord") String keyWord);


    @SelectProvider(type = UserProvider.class, method = "searchUser")
    List<Student> searchUser(@Param("user") Student student);
}
