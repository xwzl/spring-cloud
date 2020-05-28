package com.java.prepare.model.mongo;

import cn.hutool.core.date.DateUtil;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.query.Criteria;

import java.io.Serializable;
import java.util.Date;

/**
 * @author xuweizhi
 * @since 2020/05/11 13:18
 */
@Data
public class BaseMongoDO implements Serializable {

    /**
     * 主键
     */
    @Id
    private ObjectId id;

    /**
     * createTime
     */
    private Date createTime;

    /**
     * update_time
     */
    private Date updateTime;

    /**
     * 删除状态 逻辑已删除值(默认为 1) 逻辑未删除值(默认为 0)
     */
    private Integer isDelete;

    public Criteria queryInit() {
        return Criteria.where("isDelete").is(0);
    }

    public void update() {
        updateTime = DateUtil.date();
    }

    public BaseMongoDO() {
        init();
    }

    public void init() {
        isDelete = 0;
        updateTime = DateUtil.date();
        createTime = DateUtil.date();
    }
}
