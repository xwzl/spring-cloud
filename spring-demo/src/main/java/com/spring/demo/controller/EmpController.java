package com.spring.demo.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.spring.demo.mapper.ComputerMapper;
import com.spring.demo.model.dos.Computer;
import com.spring.demo.model.dos.Emp;
import com.spring.demo.service.ComputerService;
import com.spring.demo.service.EmpService;
import com.spring.demo.untils.poi.ExcelHelper;
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

    @GetMapping("/export")
    public void export(HttpServletResponse httpResponse) {
        ExcelHelper excelHelper = new ExcelHelper("aaaaa", "aaaa", "aaaa", "yyyy-MM-dd", computerService, httpResponse);
        excelHelper.setClassName("com.spring.demo.model.dos.Computer");
        excelHelper.run();
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
     * 导入员工信息，模板见......
     *
     * @param file 上传文件
     * @return 上传成功与否标志
     */
    //@PostMapping("/upload")
    //public JsonResultVO<String> uploadOfficeComputer(MultipartFile file) {
    //    String filename = file.getOriginalFilename();
    //    String msg = "导入失败！";
    //    Integer code = 0;
    //    try {
    //        ExcelUtil excelUtil = new ExcelUtil(file.getInputStream(), filename);
    //        excelUtil.setPattern("yyyy-MM-dd");
    //        List<Emp> emps = excelUtil.readValue(0, 1, excelUtil.getRowCount(0) - 1, Emp.class);
    //
    //        Integer currentYear = 2019;
    //        for (Emp emp : emps) {
    //            emp.setEmpAllmoney(new BigDecimal(0));
    //            emp.setEmpAllworkday(0);
    //            if (StringUtils.isNotEmpty(emp.getEmpIdcardnum())) {
    //                String idCard = emp.getEmpIdcardnum().substring(6, 10);
    //                Integer age = currentYear - Integer.valueOf(idCard);
    //                emp.setEmpAge(age);
    //            } else {
    //                emp.setEmpAge(0);
    //            }
    //        }
    //        boolean result = empService.saveBatch(emps);
    //        if (result) {
    //            msg = "导入成功！";
    //            code = 200;
    //
    //            operateLogsUtils.saveOperationLog(new OperateLogs("导入员工"), HttpUtils.getHttpServletRequest());
    //
    //        }
    //    } catch (Exception e) {
    //        e.printStackTrace();
    //    } finally {
    //        JsonResultVO<String> jsonResultVO = new JsonResultVO<>();
    //        jsonResultVO.setStatus(code);
    //        jsonResultVO.setMsg(msg);
    //        return jsonResultVO;
    //    }
    //
    //}

}