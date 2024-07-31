package com.java.elastic.param;

import  io.swagger.v3.oas.annotations.media.Schema;
import   io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.models.properties.StringProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * ES搜索分页参数
 *
 * @author xuweizhi
 * @since 2019-11-29
 */
@Data
@Schema("ES搜索分页参数")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EsSearchParam extends EsPageParam {
    /**
     * 搜索内容
     */
    @Schema(value = "搜索内容", required = true, dataType = StringProperty.TYPE)
    private String searchContent;
}
