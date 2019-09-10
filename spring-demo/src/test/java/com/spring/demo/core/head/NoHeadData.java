package com.spring.demo.core.head;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author xuweizhi
 */
@Data
public class NoHeadData {
    @ExcelProperty("字符串")
    private String string;
}
