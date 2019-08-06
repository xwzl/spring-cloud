package com.spring.demo.model;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
 * @since 2019-07-25
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("role")
@ApiModel(description = "角色实体")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty(value = "id",required = true,notes = "数字类型")
    private Integer id;

    @TableField("name")
    @ApiModelProperty(value = "姓名",required = true,notes = "数值类型")
    private String name;

    /**
     * 角色名称
     */
    @TableField("nameZh")
    @ApiModelProperty(value = "角色名称",required = false,notes = "数值类型")
    private String nameZh;

}
