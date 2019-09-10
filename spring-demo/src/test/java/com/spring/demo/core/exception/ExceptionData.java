package com.spring.demo.core.exception;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author xuweizhi
 */
@Data
public class ExceptionData {
    @ExcelProperty("姓名")
    private String name;
}
