package com.spring.demo.controller;


import com.spring.demo.model.dos.MybatisExpansion;
import com.spring.demo.service.MybatisExpansionService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
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
//@Api(tags = "mybatis plus 扩展服务")
public class MybatisExpansionController {

    @Resource
    private MybatisExpansionService mybatisExpansionService;

    @GetMapping
    public List<MybatisExpansion> getList() {
        return mybatisExpansionService.list();
    }

    @PostMapping
    @Operation(summary  ="测试自动填充创建时间，更新时间，逻辑删除数字")
    public void createEntity(MybatisExpansion mybatisExpansion) {
        mybatisExpansionService.save(mybatisExpansion);
    }

    @PutMapping
    @Operation(summary  ="测试更新自动填充更新字段")
    public void updateEntity(MybatisExpansion mybatisExpansion) {
        mybatisExpansionService.saveOrUpdate(mybatisExpansion);
    }

    @DeleteMapping
    @Operation(summary  ="逻辑删除")
    public void deleteEntity(MybatisExpansion mybatisExpansion) {
        mybatisExpansionService.removeById(mybatisExpansion);
    }

    @GetMapping("mybatisDemo")
    @Operation(summary  ="操作")
    public void mybatisDemo(){
        mybatisExpansionService.mybatisDemo();
    }
}
