package com.spring.demo.core.parameter;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author xuweizhi
 */
@Data
public class ParameterData {
    @ExcelProperty("姓名")
    private String name;
}
