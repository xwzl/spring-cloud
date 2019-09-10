package com.spring.demo.core.compatibility;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @author xuweizhi
 */
@Data
public class CompatibilityData extends BaseRowModel {
    @ExcelProperty("字符串标题0")
    private String string0;
    @ExcelProperty("字符串标题1")
    private String string1;
}
