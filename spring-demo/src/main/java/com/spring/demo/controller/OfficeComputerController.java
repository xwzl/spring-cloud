package com.spring.demo.controller;


import com.spring.demo.mapper.OfficeComputerMapper;
import com.spring.demo.model.OfficeComputer;
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
 * @since 2019-08-05
 */
@RestController
@RequestMapping("/demo/office-computer")
public class OfficeComputerController {

    @Autowired
    private OfficeComputerMapper officeComputerMapper;

    @GetMapping
    public List<OfficeComputer> getList(String empName, String empLevel) {
        return officeComputerMapper.getList(empLevel, empName);
    }

}
