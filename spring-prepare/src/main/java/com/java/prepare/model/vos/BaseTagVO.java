package com.java.prepare.model.vos;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 基础标签
 *
 * @author xuweizhi
 * @since 2020/03/12 15:22
 */
@Data
@ApiModel("基础标签")
public class BaseTagVO {

    @ApiModelProperty("标签主键")
    private String id;

    @ApiModelProperty("标签")
    private String tag;

    @ApiModelProperty("标签等级：1 => 一级标签 2 => 二级标签")
    private Integer level;

    @ApiModelProperty("父级标签")
    private String parentId;

}
