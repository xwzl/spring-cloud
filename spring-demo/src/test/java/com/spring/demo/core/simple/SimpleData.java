package com.spring.demo.core.simple;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author xuweizhi
 */
@Data
public class SimpleData {
    @ExcelProperty("姓名")
    private String name;
}
