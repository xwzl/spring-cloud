package com.java.prepare.model.vos;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 基础标签
 *
 * @author xuweizhi
 * @since 2020/03/12 15:22
 */
@Data
@Schema(name = "基础标签")
public class BaseTagVO {

    @Schema(name = "标签主键")
    private String id;

    @Schema(name = "标签")
    private String tag;

    @Schema(name = "标签等级：1 => 一级标签 2 => 二级标签")
    private Integer level;

    @Schema(name = "父级标签")
    private String parentId;

}
