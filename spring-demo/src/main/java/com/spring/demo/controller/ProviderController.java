package com.spring.demo.controller;

import com.spring.demo.mapper.RoleMapper;
import com.spring.demo.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Mybatis @SelectProvider 测试
 *
 * @author xuweizhi
 * @since 2019/08/05 22:32
 */
@RestController
@RequestMapping("provider")
public class ProviderController {

    @Autowired
    private RoleMapper roleMapper;

    @GetMapping
    public List<Role> getList() {
        return roleMapper.findByList();
    }
}
