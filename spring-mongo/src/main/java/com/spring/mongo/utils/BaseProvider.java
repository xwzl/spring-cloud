package com.spring.mongo.utils;


import cn.hutool.core.date.DateUtil;
import com.mongodb.client.result.UpdateResult;
import com.spring.common.model.common.PageVO;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import jakarta.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @author xuweizhi
 * @since 2020/05/15 23:30
 */
@SuppressWarnings("unchecked")
public class BaseProvider<T> implements MongoInterface<T> {

    protected final Class<T> clazz;

    protected final String className;

    @Resource
    protected MongoTemplate mongoTemplate;

    public BaseProvider() {
        Type type = this.getClass().getGenericSuperclass();
        ParameterizedType type1 = (ParameterizedType) type;
        Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
        clazz = (Class<T>) actualTypeArguments[0];
        Document document = clazz.getAnnotation(Document.class);
        // AssertUtils.assertNull(document == null, "There is no document annotation on the mongodb do class");
        className = document.value();
    }

    @Override
    public long count(Query query) {
        return mongoTemplate.count(query, clazz);
    }

    @Override
    public boolean update(Query query, Update update) {
        update.set("updateTime", DateUtil.date());
        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, clazz);
        return updateResult.getModifiedCount() > 0;
    }

    @Override
    public boolean updateById(T t, boolean isSingle) {
        Update update = new Update();
        Query query = new Query();
        query = fullUpdate(t, update, query);
        return isSingle ? update(query, update) : updateMulti(query, update);
    }

    @Override
    public boolean updateById(ObjectId id, Update update) {
        return update(Query.query(Criteria.where("_id").is(id)), update);
    }

    @Override
    public boolean updateMulti(Query query, Update update) {
        update.set("updateTime", DateUtil.date());
        UpdateResult updateResult = mongoTemplate.updateMulti(query, update, clazz);
        return updateResult.getModifiedCount() > 0;
    }

    @Override
    public PageVO<T> page(Query query, Integer pageNum, Integer pageSize) {
        long count = mongoTemplate.count(query, clazz);
        PageVO<T> tPageVO = new PageVO<>();
        tPageVO.setTotalNum(count);
        tPageVO.setPageNum(pageNum);
        tPageVO.setPageSize(pageSize);
        if ((count / pageNum + 1) < pageNum) {
            tPageVO.setList(new ArrayList<>());
            return tPageVO;
        }
        int skip = (pageNum - 1) * pageSize;

        query.skip(skip).limit(pageSize);
        List<?> objects = mongoTemplate.find(query, clazz);

        tPageVO.setList((List<T>) objects);

        return tPageVO;
    }

    @Override
    public T insert(T t) {
        return mongoTemplate.insert(t);
    }

    @Override
    public T findById(String id) {
        return mongoTemplate.findOne(Query.query(Criteria.where("_id").is(new ObjectId(id))), clazz);
    }

    @Override
    public T findById(ObjectId id) {
        return mongoTemplate.findOne(Query.query(Criteria.where("_id").is(id)), clazz);
    }

    @Override
    public T findOne(Query query) {
        return mongoTemplate.findOne(query, clazz);
    }

    @Override
    public List<T> find(Query query) {
        return mongoTemplate.find(query, clazz);
    }

    private Query fullUpdate(T t, Update update, Query query) {
        try {
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                Object value = field.get(t);
                if (value != null) {
                    if (field.getName().equals("_id")) {
                        query = Query.query(Criteria.where("_id").is(value));
                        continue;
                    }
                    update.set(field.getName(), value);
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return query;
    }
}
