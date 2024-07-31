package com.spring.demo.model.dos;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "角色实体")
public class Role implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    @Schema( required = true, description = "数字类型")
    private Integer id;

    @TableField("name")
    @Schema( required = true, description = "姓名")
    private String name;

    /**
     * 角色名称
     */
    @TableField("nameZh")
    @Schema(required = false, description = "数值类型")
    private String nameZh;

}
