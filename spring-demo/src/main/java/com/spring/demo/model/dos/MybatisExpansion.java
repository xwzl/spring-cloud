package com.spring.demo.model.dos;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * mybatis plus 扩展测试
 *
 * </p>
 *
 * @author xuweizhi
 * @since 2019-08-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("mybatis_expansion")
public class MybatisExpansion implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 苹果
     */
    @TableField("apple")
    private String apple;

    /**
     * 更新时间，执行 mybatis plus 自带的方法会拼接 SQL ，配置 SQL 拦截器，插入和更新的会被拦截
     */
    @TableField(value = "update_time", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 删除状态 逻辑已删除值(默认为 1) 逻辑未删除值(默认为 0)
     * <p>
     * 查询或者更新的时候，插入 is_delete = 0;
     * <p>
     * delete 字段 8.0.15 数据有问题,必须放在末尾，否则逻辑删除会被置位 false
     */
    @TableField(value = "is_delete", fill = FieldFill.INSERT)
    @TableLogic
    private Integer isDelete;

}
