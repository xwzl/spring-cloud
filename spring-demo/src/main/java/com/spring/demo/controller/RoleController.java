package com.spring.demo.controller;


import com.spring.demo.model.Role;
import com.spring.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xuweizhi
 * @since 2019-07-25
 */
@RestController
@RequestMapping("/demo/role")
public class RoleController {

    @Autowired
    private RoleService roleService;


    @GetMapping
    public List<Role> getRoleList() {
        return roleService.list();
    }

}
