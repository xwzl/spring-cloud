package com.spring.demo.model.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @author xuweizhi
 * @since 2019-08-05
 */
@Data
@ToString
public class ComputerModel{

    @ExcelProperty(value = "编号", index = 0)
    private String assetNumber;

    @ExcelProperty(value = "类型", index = 1)
    private String assetType;

    @ExcelProperty(value = "品牌", index = 2)
    private String brand;

    @ExcelProperty(value = "电脑来源", index = 3)
    private String computerSource;

    @ExcelProperty(value = "贡献公司", index = 4)
    private String attributionCompany;

    @ExcelProperty(value = "使用公司", index = 5)
    private String useCompany;

    @ExcelProperty(value = "使用部门", index = 6)
    private String useDepartment;

    @ExcelProperty(value = "使用者", index = 7)
    private String owner;

    @ExcelProperty(value = "使用状态", index = 8)
    private String useOfState;

    @ExcelProperty(value = "机器配置", index = 9)
    private String machineConfiguration;

    @ExcelProperty(value = "机器地址", index = 10)
    private String machineAddr;

    @ExcelProperty(value = "机器状态", index = 11)
    private String machineState;

    @ExcelProperty(value = "质量", index = 12)
    private Integer quantity;

    @ExcelProperty(value = "单位", index = 13)
    private String unit;

    @ExcelProperty(value = "厂商", index = 14)
    private String tradeName;

    @ExcelProperty(value = "联系人", index = 15)
    private String contact;

    @ExcelProperty(value = "电话号码", index = 16)
    private String iphoneNumber;

}
