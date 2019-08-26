package com.spring.demo.model.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author xuweizhi
 * @since 2019-08-05
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("computer")
public class Computer implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 办公电脑主键
     */
    @TableId(value = "office_id", type = IdType.AUTO)
    private Integer officeId;

    /**
     * 员工姓名
     */
    @TableField("owner")
    private String owner;

    /**
     * 资产编号
     */
    @TableField("asset_number")
    private String assetNumber;

    /**
     * 资产类型
     */
    @TableField("asset_type")
    private String assetType;

    /**
     * 资产品牌
     */
    @TableField("brand")
    private String brand;

    /**
     * 电脑来源
     */
    @TableField("computer_source")
    private String computerSource;

    /**
     * 归属公司
     */
    @TableField("attribution_company")
    private String attributionCompany;

    /**
     * 使用公司
     */
    @TableField("use_company")
    private String useCompany;

    /**
     * 使用部门
     */
    @TableField("use_department")
    private String useDepartment;

    /**
     * 使用状态
     */
    @TableField("use_of_state")
    private String useOfState;

    /**
     * 机器状态
     */
    @TableField("machine_state")
    private String machineState;

    /**
     * 机器配置
     */
    @TableField("machine_configuration")
    private String machineConfiguration;

    /**
     * 机器位置
     */
    @TableField("machine_addr")
    private String machineAddr;

    /**
     * 资产数量
     */
    @TableField("quantity")
    private Integer quantity;

    /**
     * 资产单位
     */
    @TableField("unit")
    private String unit;

    /**
     * 厂商名称
     */
    @TableField("trade_name")
    private String tradeName;

    /**
     * 电话号
     */
    @TableField("iphone_number")
    private String iphoneNumber;

    /**
     * 联系人
     */
    @TableField("contact")
    private String contact;


}
