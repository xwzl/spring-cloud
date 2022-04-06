package com.spring.demo.model.dos;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author xuweizhi
 * @since 2019-08-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("computer")
//@Api("测试类")
public class Computer implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "office_id", type = IdType.AUTO)
    private Integer officeId;

    @TableField("asset_number")
    @ExcelProperty(value = "编号", index = 0)
    private String assetNumber;

    @TableField("asset_type")
    @ExcelProperty(value = "类型", index = 1)
    private String assetType;

    @TableField("brand")
    @ExcelProperty(value = "品牌", index = 2)
    private String brand;

    @TableField("computer_source")
    @ExcelProperty(value = "电脑来源", index = 3)
    private String computerSource;

    @TableField("attribution_company")
    @ExcelProperty(value = "贡献公司", index = 4)
    private String attributionCompany;

    @TableField("use_company")
    @ExcelProperty(value = "使用公司", index = 5)
    private String useCompany;

    @TableField("use_department")
    @ExcelProperty(value = "使用部门", index = 6)
    private String useDepartment;

    @TableField("owner")
    @ExcelProperty(value = "使用者", index = 7)
    private String owner;

    @TableField("use_of_state")
    @ExcelProperty(value = "使用状态", index = 8)
    private String useOfState;

    @TableField("machine_configuration")
    @ExcelProperty(value = "机器配置", index = 9)
    private String machineConfiguration;

    @TableField("machine_addr")
    @ExcelProperty(value = "机器地址", index = 10)
    private String machineAddr;

    @TableField("machine_state")
    @ExcelProperty(value = "机器状态", index = 11)
    private String machineState;

    @TableField("quantity")
    @ExcelProperty(value = "质量", index = 12)
    private Integer quantity;

    @TableField("unit")
    @ExcelProperty(value = "单位", index = 13)
    private String unit;

    @TableField("trade_name")
    @ExcelProperty(value = "厂商", index = 14)
    private String tradeName;

    @TableField("contact")
    @ExcelProperty(value = "联系人", index = 15)
    private String contact;

    @TableField("iphone_number")
    @ExcelProperty(value = "电话号码", index = 16)
    private String iphoneNumber;


}
