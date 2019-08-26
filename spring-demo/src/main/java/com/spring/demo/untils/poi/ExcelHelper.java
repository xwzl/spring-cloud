package com.spring.demo.untils.poi;

import com.spring.demo.service.BaseService;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author xuweizhi
 * @since 2019/06/02 20:34
 */
@Data
public class ExcelHelper {

    /**
     * shell表格的表头
     */
    private String title;

    /**
     * shell表名称
     */
    private String sheetName;

    /**
     * 导出 excelName 名称
     */
    private String excelName;

    /**
     * 事件日期转换规则
     */
    private String pattern;

    private BaseService<T> iService;

    private HttpServletResponse response;

    private String className;

    public ExcelHelper(String title, String sheetName, String excelName, BaseService iService, HttpServletResponse response, String className) {
        this.title = title;
        this.sheetName = sheetName;
        this.excelName = excelName;
        this.pattern = "yyyy-MM-dd HH:mm:ss";
        this.iService = iService;
        this.response = response;
        this.className = className;
    }

    public ExcelHelper(String title, String sheetName, String excelName, String pattern, BaseService iService, HttpServletResponse response) {
        this.title = title;
        this.sheetName = sheetName;
        this.excelName = excelName;
        this.pattern = pattern;
        this.iService = iService;
        this.response = response;
    }

    public void run() {
        try {
            List<T> officeComputers = iService.list();

            ExcelUtil excelUtil = new ExcelUtil(new XSSFWorkbook());
            List<List<String>> bodyDate = excelUtil.getBodyDate(officeComputers, className, pattern);
            excelUtil.write(0, bodyDate, title, sheetName);

            response.setContentType("application/binary;charset=UTF-8");
            String fileName = new String(excelName.getBytes(), "ISO8859-1");
            response.setHeader("Content-disposition", "attachment; filename=" + fileName + ".xlsx");

            excelUtil.getWorkbook().write(response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
