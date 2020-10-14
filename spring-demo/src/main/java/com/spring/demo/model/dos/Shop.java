package com.spring.demo.model.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author xuweizhi
 * @since 2020/10/14 21:27
 */
@Data
@TableName("shop")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Shop {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private Long bookId;

    private String shopName;
}
