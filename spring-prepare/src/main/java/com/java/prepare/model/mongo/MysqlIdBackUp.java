package com.java.prepare.model.mongo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

/**
 * @author xuweizhi
 * @since 2020/05/27 18:31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@Document(collection = "MysqlIdBackUp")
public class MysqlIdBackUp {

    @Id
    private ObjectId id;

    private Long mysqlId;

    private String mongoId;

    private Integer mark;

    /**
     * 1 成功 2 失败
     */
    private Integer successState;

    public Query getQuery(int mark, Long mysqlId) {
        return Query.query(Criteria.where("mysqlId").is(mysqlId).and("mark").is(mark).and("successState").is(1));
    }

    public MysqlIdBackUp(Long mysqlId, Integer mark, Integer successState) {
        this.mysqlId = mysqlId;
        this.mark = mark;
        this.successState = successState;
    }
}
