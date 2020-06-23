package com.java.prepare.until.mongo;

import com.spring.common.model.common.PageVO;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;


/**
 *
 */
public class MongoPageUtil {


    public static <T> PageVO<T> pageUtil(Query query, Class<T> clazz, Integer pageNum, Integer pageSize, MongoTemplate mongoTemplate) {
        long count = mongoTemplate.count(query, clazz);
        query.limit(pageSize);
        query.skip(pageSize*(pageNum-1));
        List<?> objects = mongoTemplate.find(query, clazz);
        PageVO<T> tPageVO = new PageVO<>();
        tPageVO.setList((List<T>) objects);
        tPageVO.setTotalNum(count);
        tPageVO.setPageNum(pageNum);
        tPageVO.setPageSize(pageSize);
        return tPageVO;
    }

}
