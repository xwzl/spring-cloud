package com.spring.demo.controller;


import com.spring.demo.model.dos.MybatisExpansion;
import com.spring.demo.service.MybatisExpansionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * mybatis plus 扩展测试
 * 前端控制器
 *
 * @author xuweizhi
 * @since 2019-08-07
 */
@RestController
@RequestMapping("/mybatis-expansion")
public class MybatisExpansionController {

    @Autowired
    private MybatisExpansionService mybatisExpansionService;

    @GetMapping
    public List<MybatisExpansion> getList() {
        return mybatisExpansionService.list();
    }

    @PostMapping
    public void createEntity(MybatisExpansion mybatisExpansion) {
        mybatisExpansionService.save(mybatisExpansion);
    }

    @PutMapping
    public void updateEntity(MybatisExpansion mybatisExpansion) {
        mybatisExpansionService.saveOrUpdate(mybatisExpansion);
    }

    @DeleteMapping
    public void deleteEntity(MybatisExpansion mybatisExpansion) {
        mybatisExpansionService.removeById(mybatisExpansion);
    }

}
