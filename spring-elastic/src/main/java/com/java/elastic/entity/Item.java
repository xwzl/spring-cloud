package com.java.elastic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author xuweizhi
 * @since 2019/07/16 15:46
 */
@Data
@Document(indexName = "item",createIndex = false)
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    private Long id;

    @Field(type = FieldType.Keyword)
    private String title;

    @Field(type = FieldType.Keyword)
    private String category;

    @Field(type = FieldType.Keyword)
    private String brand;

    @Field(type = FieldType.Double)
    private Double price;

    @Field(index = false, type = FieldType.Keyword)
    private String images;

}

