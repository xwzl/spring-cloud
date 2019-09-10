package com.spring.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.spring.demo.annotation.AopConfig;
import com.spring.demo.mapper.ComputerMapper;
import com.spring.demo.model.dos.Computer;
import com.spring.demo.model.dos.Emp;
import com.spring.demo.service.ComputerService;
import com.spring.demo.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author xuweizhi
 * @since 2019-08-01
 */
@AopConfig
@RestController
@RequestMapping("/emp")
public class EmpController {


    @Autowired
    private EmpService empService;

    @Autowired
    private ComputerMapper computerMapper;

    @Autowired
    private ComputerService computerService;

    @GetMapping("list")
    public List<Computer> getList(String empName, String empLevel) {
        return computerMapper.getList(empLevel, empName);
    }

    @GetMapping
    public List<Emp> test(HttpServletResponse response) {
        return empService.list();
    }

    @DeleteMapping
    public void delete() {
        empService.delete(new Emp());
    }

    @GetMapping("/list1")
    public List<Computer> getList1(Computer computer) {
        QueryWrapper<Computer> query = new QueryWrapper<>();
        query.eq(StringUtils.isNotEmpty(computer.getAssetType()), "asset_type", computer.getAssetType())
                .eq(StringUtils.isNotEmpty(computer.getBrand()), "brand", computer.getBrand())
                .like(StringUtils.isNotEmpty(computer.getOwner()), "owner", computer.getOwner())
                .like(StringUtils.isNotEmpty(computer.getContact()), "contact", computer.getContact());
        return computerService.list(query);
    }

    /**
     * 史上大 bug 集合
     * <p>
     * 期望 sql 结果： brand = ? and ( assert_number = ? or computer_source = ?)
     * <p>
     * sql 结果： brand = ? and assert_number = ? or computer_source = ?
     *
     * @param computer 最坑的一次 bug
     */
    @GetMapping("/list2")
    public List<Computer> orSelectBug(Computer computer, String keyWord) {
        QueryWrapper<Computer> query = new QueryWrapper<>();
        query.eq(StringUtils.isNotEmpty(computer.getBrand()), "brand", computer.getBrand());
        query.and(StringUtils.isNotEmpty(keyWord), i -> i.like("asset_number", keyWord).or().like("computer_source", keyWord));
        return computerService.list(query);
    }

    @GetMapping("/list3")
    public List<Computer> getList3(Computer computer) {
        return computerMapper.list1(computer);
    }


}
