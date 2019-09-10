package com.spring.demo.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.format.DateTimeFormat;
import com.alibaba.excel.annotation.format.NumberFormat;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.spring.demo.listener.CustomStringStringConverter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * 测试 excel 导出
 *
 * @author xuweizhi
 * @since 2019/09/10 15:15
 */
@ContentRowHeight(10)
@HeadRowHeight(20)
@ColumnWidth(25)
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class DemoModel {

    @ExcelProperty(value = "编号", index = 0)
    private Integer indexNumber;

    /**
     * 我想所有的 字符串起前面加上"自定义："三个字
     */
    @ExcelProperty(value = "字符串标题", converter = CustomStringStringConverter.class, index = 1)
    private String string;

    /**
     * 我想写到excel 用年月日的格式
     */
    @DateTimeFormat("yyyy年MM月dd日HH时mm分ss秒")
    @ExcelProperty(value = "日期标题", index = 2)
    private Date date;

    /**
     * 我想写到excel 用百分比表示
     */
    @NumberFormat("#.##%")
    @ExcelProperty(value = "数字标题", index = 3)
    private Double doubleData;

}
