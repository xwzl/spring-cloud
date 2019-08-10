package com.spring.demo.controller;


import com.spring.demo.model.HyyEmp;
import com.spring.demo.service.HyyEmpService;
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
@RestController
@RequestMapping("/hyy")
public class HyyEmpController {


    @Autowired
    private HyyEmpService hyyEmpService;

    @GetMapping
    public List<HyyEmp> test(HttpServletResponse response) {
        return hyyEmpService.list();
    }


    @DeleteMapping
    public void delete() {
        hyyEmpService.delete(new HyyEmp());
    }
}
