package com.spring.demo.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author xuweizhi
 * @since 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("hyy_emp")
@ApiModel("hyy")
public class HyyEmp {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "id", required = false)
    private Integer id;

    /**
     * 工号
     */
    @TableField("emp_work_id")
    private Integer empWorkId;

    /**
     * 员工姓名
     */
    @TableField("emp_name")
    private String empName;

    /**
     * 员工所属部门名字
     */
    @TableField("emp_dep_name")
    private String empDepName;

    /**
     * 员工岗位名称
     */
    @TableField("emp_post_name")
    private String empPostName;

    /**
     * 员工职级
     */
    @TableField("emp_level")
    private String empLevel;

    /**
     * 员工工作区域
     */
    @TableField("emp_workarea")
    private String empWorkarea;

    /**
     * 员工技术等级
     */
    @TableField("emp_skill")
    private String empSkill;

    /**
     * 员工电话号码
     */
    @TableField("emp_phone")
    private String empPhone;

    /**
     * 员工联系方式
     */
    @TableField("emp_idcardnum")
    private String empIdcardnum;

    /**
     * 员工性别
     */
    @TableField("emp_sex")
    private String empSex;

    /**
     * 员工年龄
     */
    @TableField("emp_age")
    private Integer empAge;

    /**
     * 员工民族
     */
    @TableField("emp_nation")
    private String empNation;

    /**
     * 员工入项时间
     */
    @TableField("emp_in")
    private LocalDate empIn;


    /**
     * 员工离项时间
     */
    @TableField("emp_out")
    private LocalDate empOut;

    /**
     * 员工预计投入工作日
     */
    @TableField("emp_allworkday")
    private Integer empAllworkday;

    /**
     * 员工预计报价金额
     */
    @TableField("emp_allmoney")
    private BigDecimal empAllmoney;

    /**
     * 员工备注信息
     */
    @TableField("emp_remarks")
    private String empRemarks;

    /**
     * 员工入项状态
     */
    @TableField("emp_status")
    private Integer empStatus;

    /**
     * 员工入职时间
     */
    @TableField("emp_entry_time")
    private LocalDateTime empEntryTime;

    /**
     * 员工离职时间
     */
    @TableField("emp_separation_time")
    private LocalDateTime empSeparationTime;

    /**
     * 员工就职状态
     */
    @TableField("emp_work_status")
    private String empWorkStatus;


}
