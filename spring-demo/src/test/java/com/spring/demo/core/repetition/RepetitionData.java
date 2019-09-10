package com.spring.demo.core.repetition;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @author xuweizhi
 */
@Data
public class RepetitionData {
    @ExcelProperty("字符串")
    private String string;
}
