package com.spring.demo.controller;

import com.spring.demo.untils.excel.ExcelUtil;
import com.spring.demo.untils.excel.ExportInfo;
import com.spring.demo.untils.excel.ImportInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * 这是阿里的 easyExcel
 *
 * @author xuweizhi
 */
@RestController
public class ExcelController {

    /**
     * 读取 Excel（允许多个 sheet）
     */
    @RequestMapping(value = "readExcelWithSheets", method = RequestMethod.POST)
    public Object readExcelWithSheets(MultipartFile excel) {
        return ExcelUtil.readExcel(excel, new ImportInfo());
    }

    /**
     * 读取 Excel（指定某个 sheet）
     */
    @RequestMapping(value = "readExcel", method = RequestMethod.POST)
    public Object readExcel(MultipartFile excel, int sheetNo,
                            @RequestParam(defaultValue = "1") int headLineNum) {
        return ExcelUtil.readExcel(excel, new ImportInfo(), sheetNo, headLineNum);
    }

    /**
     * 导出 Excel（一个 sheet）
     */
    @RequestMapping(value = "writeExcel", method = RequestMethod.GET)
    public void writeExcel(HttpServletResponse response) throws IOException {
        List<ExportInfo> list = getList();
        String fileName = "一个 Excel 文件";
        String sheetName = "第一个 sheet";

        ExcelUtil.writeExcel(response, list, fileName, sheetName, new ExportInfo());
    }

    /**
     * 导出 Excel（多个 sheet）
     */
    @RequestMapping(value = "writeExcelWithSheets", method = RequestMethod.GET)
    public void writeExcelWithSheets(HttpServletResponse response) throws IOException {
        List<ExportInfo> list = getList();
        String fileName = "一个 Excel 文件";
        String sheetName1 = "第一个 sheet";
        String sheetName2 = "第二个 sheet";
        String sheetName3 = "第三个 sheet";

        ExcelUtil.writeExcelWithSheets(response, list, fileName, sheetName1, new ExportInfo())
                .write(list, sheetName2, new ExportInfo())
                .write(list, sheetName3, new ExportInfo())
                .finish();
    }

    private List<ExportInfo> getList() {
        List<ExportInfo> list = new ArrayList<>();
        ExportInfo model1 = new ExportInfo();
        model1.setName("howie");
        model1.setAge("19");
        model1.setAddress("123456789");
        model1.setEmail("123456789@gmail.com");
        list.add(model1);
        ExportInfo model2 = new ExportInfo();
        model2.setName("harry");
        model2.setAge("20");
        model2.setAddress("198752233");
        model2.setEmail("198752233@gmail.com");
        list.add(model2);
        return list;
    }
}
