package com.spring.mongo.model;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * MongoDB 测试
 *
 * @author xuweizhi
 * @since 2020/05/07 14:34
 */
@Data
@Document(collection = "student")
public class Student {

    private ObjectId id;

    private String name;

    private Integer math;

    private Integer english;

    private Integer chinese;

}
