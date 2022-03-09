package com.java.elastic.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 搜索中的商品信息
 * @author 62424
 */
@Data
@Document(indexName = "product_db")
public class EsProduct implements Serializable {

    @Serial
    private static final long serialVersionUID = -1L;

    @Id
    private Long id;
    @Field(type = FieldType.Keyword)
    private String productSn;
    private Long brandId;
    @Field(type = FieldType.Keyword)
    private String brandName;
    private Long productCategoryId;
    @Field(type = FieldType.Keyword)
    private String productCategoryName;
    private String pic;
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String name;
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String subTitle;
    @Field(analyzer = "ik_max_word", type = FieldType.Text)
    private String keywords;

    /**
     * 非促销时的当前价格
     */
    private BigDecimal price;

    /**
     * 促销价格
     */
    private BigDecimal promotionPrice;

    /**
     * 市场价
     */
    private BigDecimal originalPrice;

    private Integer sale;
    private Integer newStatus;

    private Integer recommandStatus;
    private Integer stock;
    private Integer promotionType;
    private Integer sort;


}
