package com.java.elastic.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel("ES搜索分页参数")
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class EsSearchParam extends EsPageParam {
    /**
     * 搜索内容
     */
    @ApiModelProperty(value = "搜索内容", required = true, dataType = StringProperty.TYPE)
    private String searchContent;
}
