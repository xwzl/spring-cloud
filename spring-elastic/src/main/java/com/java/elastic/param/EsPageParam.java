package com.java.elastic.param;

import  io.swagger.v3.oas.annotations.media.Schema;
import   io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.models.properties.IntegerProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 分页参数
 *
 * @author xuweizhi
 * @since 2019-11-29
 */
@Data
@Schema("基础分页参数")
@ToString(callSuper = true)
public class EsPageParam implements Serializable {
    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 页码
     */
    @Schema(value = "页码", required = true, dataType = IntegerProperty.TYPE, example = "1")
    private Integer pageNumber = 1;

    /**
     * 页数
     */
    @Schema(value = "页数", required = true, dataType = IntegerProperty.TYPE, example = "10")
    private Integer pageSize = 10;
}
