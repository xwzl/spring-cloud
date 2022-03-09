package com.spring.demo.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.spring.demo.listener.ComputerListener;
import com.spring.demo.model.dos.Computer;
import com.spring.demo.model.excel.ComputerModel;
import com.spring.demo.model.excel.DemoModel;
import com.spring.demo.model.excel.MultiLineHeadExcelModel;
import com.spring.common.model.common.ResultVO;
import com.spring.demo.service.ComputerService;
import com.spring.demo.untils.ListCopy;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jetbrains.annotations.Contract;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.alibaba.excel.EasyExcelFactory.write;
import static com.spring.demo.untils.ResponseUtils.excelResponse;


/**
 * https://github.com/HowieYuan/easyexcel-encapsulation
 *
 * @author xuweizhi
 * @since 2019-09-10
 */
@RestController
@RequestMapping("/excel")
@Api(tags = "EasyExcel 导入导入导出测试")
public class ExcelController {

    private final ComputerService computerService;

    private final ListCopy<ComputerModel, Computer> modelToComputer;

    private final ListCopy<Computer, ComputerModel> computerToModel;

    @Contract(pure = true)
    public ExcelController(ComputerService computerService, ListCopy<ComputerModel, Computer> modelToComputer, ListCopy<Computer, ComputerModel> computerToModel) {
        this.computerService = computerService;
        this.modelToComputer = modelToComputer;
        this.computerToModel = computerToModel;
    }

    /**
     * 文件下载
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link ComputerModel}
     * <p>
     * 2. 设置返回的 参数
     * <p>
     * 3. 直接写，这里注意，finish的时候会自动关闭OutputStream,当然你外面再关闭流问题不大
     */
    @GetMapping("download")
    @ApiOperation("文件下载")
    public void download(HttpServletResponse response) throws IOException {
        excelResponse(response, "办公电脑");
        List<Computer> list = computerService.list();
        List<ComputerModel> result = new ArrayList<>();
        ComputerModel computerModel;
        for (Computer computer : list) {
            computerModel = new ComputerModel();
            BeanUtils.copyProperties(computer, computerModel);
            result.add(computerModel);
        }
        write(response.getOutputStream(), ComputerModel.class).sheet("模板").doWrite(result);
    }

    /**
     * 多个 sheet 表单下载
     */
    @GetMapping("multipleSheets")
    @ApiOperation("多个 shell 表单下载")
    public void multipleSheets(HttpServletResponse response) throws IOException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        excelResponse(response, "多表格提交");
        List<ComputerModel> list = computerToModel.copyList(computerService.list(), ComputerModel.class);
        ExcelWriter excelWriter = write(response.getOutputStream(), ComputerModel.class).build();
        for (int i = 0; i < list.size(); i += 10) {
            WriteSheet writeSheet = EasyExcel.writerSheet(i / 10, "第" + (i / 10 + 1) + "页").build();
            excelWriter.write(list.subList(i, Math.min(i + 10, list.size())), writeSheet);
        }
        excelWriter.finish();
    }

    /**
     * 多表头下载
     */
    @GetMapping("multipleHead")
    @ApiOperation("多表头下载")
    public void multipleHead(HttpServletResponse response) throws IOException {
        excelResponse(response, "多表头测试");
        write(response.getOutputStream(), MultiLineHeadExcelModel.class).sheet("第一页").doWrite(new ArrayList());
    }

    /**
     * 自定义下载
     */
    @GetMapping("customize")
    @ApiOperation("自定义表格格式")
    public void customize(HttpServletResponse response) throws IOException {
        excelResponse(response, "自定义");
        List<DemoModel> data = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            data.add(new DemoModel(i, "111" + i, new Date(), 0.56));
        }
        write(response.getOutputStream(), DemoModel.class).sheet("第一页").doWrite(data);
    }

    /**
     * 文件上传
     * <p>
     * 1. 创建excel对应的实体对象 参照{@link ComputerModel}
     * <p>
     * 2. 由于默认异步读取excel，所以需要创建excel一行一行的回调监听器，参照{@link ComputerListener}
     * <p>
     * 3. 直接读即可
     * EasyExcel.read(file.getInputStream(), ComputerModel.class, computerListener).sheet().doRead();
     */
    @PostMapping("upload")
    @ApiOperation("excel 文件上传测试")
    public ResultVO<String> upload(@RequestParam(value = "file") MultipartFile file) throws IOException, NoSuchMethodException, IllegalAccessException, InstantiationException {
        ComputerListener listener = new ComputerListener();
        // 获取 excel 解析对象
        ExcelReader excelReader = EasyExcel.read(file.getInputStream(), ComputerModel.class, listener).build();
        // 获取 sheet 数量
        List<ReadSheet> sheetList = excelReader.excelExecutor().sheetList();
        for (int i = 0; i < sheetList.size(); i++) {
            // 读取每个 sheet 表数据
            excelReader.read(EasyExcel.readSheet(i).build());
        }
        List<ComputerModel> data = listener.getData();

        List<Computer> list = modelToComputer.copyList(data, Computer.class);
        computerService.saveBatch(list);

        return new ResultVO<>();
    }

}
