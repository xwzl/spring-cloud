package com.spring.demo.model.dos;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.ArrayList;
import java.util.List;

/**
 * 书
 *
 * @author xuweizhi
 * @since 2020/10/14 21:25
 */
@Data
@TableName("book")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
public class Book {

    /**
     * 主键
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    private String bookName;

    private Shop shop;

    private List<Author> authors = new ArrayList<>();


}
