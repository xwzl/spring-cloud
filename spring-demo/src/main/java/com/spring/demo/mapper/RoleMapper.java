package com.spring.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.demo.model.Role;
import com.spring.demo.service.provider.RoleProvider;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author xuweizhi
 * @since 2019-07-25
 */
public interface RoleMapper extends BaseMapper<Role> {


    /**
     * 哈哈
     */
    @SelectProvider(type = RoleProvider.class, method = "buildGetUsersByName")
    List<Role> findByList();

}
