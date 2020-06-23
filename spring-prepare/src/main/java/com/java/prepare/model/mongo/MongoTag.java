package com.java.prepare.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * @author: chenyunsong
 * @date: 2020-05-12 14:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Builder(toBuilder = true)
@Document(collection = "tag")
public class MongoTag extends BaseMongoDO {

    /**
     * 标签
     */
    private String tag;

    /**
     * 标签地址
     */
    private String imgUrl;

    private String parentId;

    private String searchId;

    private Integer rank;

    private List<String> other;

}
