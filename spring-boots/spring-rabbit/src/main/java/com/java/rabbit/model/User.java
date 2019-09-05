package com.java.rabbit.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author xuweizhi
 * @since 2019-07-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "u_id", type = IdType.AUTO)
    private Integer uId;

    @TableField("address")
    private String address;

    @TableField("apartment")
    private String apartment;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("password")
    private String password;

    @TableField("phone_number")
    private String phoneNumber;

    @TableField("role")
    private Integer role;

    @TableField("username")
    private String username;

}
