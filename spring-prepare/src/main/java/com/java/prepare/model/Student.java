package com.java.prepare.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.spring.common.model.model.dos.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * 学生表
 *
 * @author xuweizhi
 * @since 2020-05-25
 */
@Data
@ToString
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("student")
public class Student extends BaseDO<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 用户名
     */
    @TableField("user_name")
    private String userName;

    /**
     * 年龄
     */
    @TableField("age")
    private Integer age;

    /**
     * 身份证
     */
    @TableField("id_card")
    private String idCard;

    /**
     * 地址
     */
    @TableField("address")
    private String address;

}
