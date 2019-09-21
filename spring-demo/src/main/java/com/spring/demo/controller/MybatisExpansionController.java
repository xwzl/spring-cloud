package com.spring.demo.controller;


import com.spring.demo.model.dos.MybatisExpansion;
import com.spring.demo.service.MybatisExpansionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "mybatis plus 扩展服务")
public class MybatisExpansionController {

    @Autowired
    private MybatisExpansionService mybatisExpansionService;

    @GetMapping
    public List<MybatisExpansion> getList() {
        return mybatisExpansionService.list();
    }

    @PostMapping
    @ApiOperation("测试自动填充创建时间，更新时间，逻辑删除数字")
    public void createEntity(MybatisExpansion mybatisExpansion) {
        mybatisExpansionService.save(mybatisExpansion);
    }

    @PutMapping
    @ApiOperation("测试更新自动填充更新字段")
    public void updateEntity(MybatisExpansion mybatisExpansion) {
        mybatisExpansionService.saveOrUpdate(mybatisExpansion);
    }

    @DeleteMapping
    @ApiOperation("逻辑删除")
    public void deleteEntity(MybatisExpansion mybatisExpansion) {
        mybatisExpansionService.removeById(mybatisExpansion);
    }

}
