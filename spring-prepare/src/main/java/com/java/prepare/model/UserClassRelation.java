package com.java.prepare.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *
 * @author xuweizhi
 * @since 2020-05-27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user_class_relation")
public class UserClassRelation implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户课程关系表
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 用户主键
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 课程表主键
     */
    @TableField("class_id")
    private Long classId;


}
