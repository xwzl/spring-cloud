package com.spring.mongo.utils;

import com.spring.common.model.common.PageVO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * @author xuweizhi
 * @since 2020/05/15 22:33
 */
public interface MongoInterface<T> {

    long count(Query query);

    boolean update(Query query, Update update);

    boolean updateById(T t, boolean isSingle);

    boolean updateById(ObjectId id, Update update);

    boolean updateMulti(Query query, Update update);

    T insert(T t);

    T findById(String id);

    T findById(ObjectId id);

    T findOne(Query query);

    List<T> find(Query query);

    PageVO<T> page(Query query, Integer pageNum, Integer pageSize);
}
