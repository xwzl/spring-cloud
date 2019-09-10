package com.spring.demo.core.style;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author xuweizhi
 */
@Data
public class StyleData {
    @ExcelProperty("字符串")
    private String string;
    @ExcelProperty("字符串1")
    private String string1;
}
