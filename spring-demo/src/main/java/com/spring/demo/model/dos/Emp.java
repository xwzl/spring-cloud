package com.spring.demo.model.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author xuweizhi
 * @since 2019-08-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("emp")
@Schema(description = "hyy")
public class Emp {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    @Schema(description = "id", required = false)
    private Integer id;

    /**
     * 员工姓名
     */
    @TableField("emp_name")
    private String empName;

    /**
     * 员工年龄
     */
    @TableField("emp_age")
    private Integer empAge;
}
