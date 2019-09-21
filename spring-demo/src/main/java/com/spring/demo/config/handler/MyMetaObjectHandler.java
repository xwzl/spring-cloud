package com.spring.demo.config.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 自动注入
 *
 * @author xuweizhi
 * @since 2019-08-07
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    /**
     * 这边是 Java 对象
     *
     * @param metaObject 导入
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        this.setInsertFieldValByName("createTime", LocalDateTime.now(), metaObject);
        this.setInsertFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        this.setInsertFieldValByName("isDelete", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setUpdateFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }
}
