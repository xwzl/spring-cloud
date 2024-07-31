package com.java.elastic.entity;

import  io.swagger.v3.oas.annotations.media.Schema;
import   io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.models.properties.LongProperty;
import io.swagger.models.properties.StringProperty;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * ES城市 Entity
 *
 * @author xuweizhi
 * @since 2019-11-29
 */
@Data
@Schema("ES城市")
@Document(indexName = "city_index")
public class EsCityEntity implements Serializable {
    /**
     * 序列化版本号
     */
    private static final long serialVersionUID = 1L;

    /**
     * 城市编号
     */
    @Id
    @NotNull
    @Schema(value = "城市编号", required = true, dataType = LongProperty.TYPE, example = "0")
    private Long id;

    /**
     * 省份编号
     */
    @NotNull
    @Schema(value = "省份编号", required = true, dataType = LongProperty.TYPE, example = "0")
    private Long provinceId;

    /**
     * 城市名称
     * <p>
     * Field(type = FieldType.Keyword) 报错:
     * mapper [cityName] of different type, current_type [text], merged_type [keyword]
     */
    @NotBlank
//    @Field(type = FieldType.Keyword)
    @Schema(value = "城市名称", required = true, dataType = StringProperty.TYPE)
    private String cityName;

    /**
     * 描述
     */
    @NotBlank
    @Schema(value = "描述", dataType = StringProperty.TYPE)
    private String description;
}
