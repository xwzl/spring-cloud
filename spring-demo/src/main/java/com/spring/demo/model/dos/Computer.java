package com.spring.demo.model.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.spring.demo.annotation.PoiHelper;
import com.spring.demo.untils.poi.TypedConstant;
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

    @TableId(value = "office_id", type = IdType.AUTO)
    private Integer officeId;

    @TableField("asset_number")
    @PoiHelper(value = "资产编号",type = TypedConstant.STRING)
    private String assetNumber;

    @TableField("asset_type")
    @PoiHelper(value = "资产类型",type = TypedConstant.STRING)
    private String assetType;

    @TableField("brand")
    @PoiHelper(value = "资产品牌",type = TypedConstant.STRING)
    private String brand;

    @TableField("computer_source")
    @PoiHelper(value = "资产来源",type = TypedConstant.STRING)
    private String computerSource;

    @TableField("attribution_company")
    @PoiHelper(value = "归属公司",type = TypedConstant.STRING)
    private String attributionCompany;

    @TableField("use_company")
    @PoiHelper(value = "使用公司",type = TypedConstant.STRING)
    private String useCompany;

    @TableField("use_department")
    @PoiHelper(value = "使用部门",type = TypedConstant.STRING)
    private String useDepartment;

    @TableField("owner")
    @PoiHelper(value = "使用人员",type = TypedConstant.STRING)
    private String owner;

    @TableField("use_of_state")
    @PoiHelper(value = "使用状态",type = TypedConstant.STRING)
    private String useOfState;

    @TableField("machine_configuration")
    @PoiHelper(value = "资产配置信息",type = TypedConstant.STRING)
    private String machineConfiguration;

    @TableField("machine_addr")
    @PoiHelper(value = "资产所在位置",type = TypedConstant.STRING)
    private String machineAddr;

    @TableField("machine_state")
    @PoiHelper(value = "资产状态",type = TypedConstant.STRING)
    private String machineState;

    @TableField("quantity")
    @PoiHelper(value = "资产数量",type = TypedConstant.NUMBER)
    private Integer quantity;

    @TableField("unit")
    @PoiHelper(value = "资产单位",type = TypedConstant.STRING)
    private String unit;

    @TableField("trade_name")
    @PoiHelper(value = "厂商名称",type = TypedConstant.STRING)
    private String tradeName;

    @TableField("contact")
    @PoiHelper(value = "联系人",type = TypedConstant.STRING)
    private String contact;

    @TableField("iphone_number")
    @PoiHelper(value = "电话号",type = TypedConstant.STRING)
    private String iphoneNumber;


}
