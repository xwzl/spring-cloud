package com.spring.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.spring.demo.model.dos.Author;
import com.spring.demo.model.dos.Book;
import com.spring.demo.model.dos.Shop;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author xuweizhi
 * @since 2020/10/14 21:32
 */
@Mapper
public interface BookMapper extends BaseMapper<Book> {

    /**
     * authors Java 中的字段
     * id mysql book 表中对应的字段
     *
     * @return 结果
     */
    @Select("select * from book")
    @Results({@Result(property = "shop", javaType = Shop.class, column = "id", one =
    @One(select = "com.spring.demo.mapper.BookMapper.shopById")), @Result(property = "authors",
            javaType = List.class, column = "id", many = @Many(select = "com.spring.demo.mapper.BookMapper.authorList"))})
    List<Book> bookList();

    @Select("select * from author where book_id = #{bookId}")
    List<Author> authorList(@Param("bookId") Long bookId);

    @Select("select * from shop where book_id = #{bookId}")
    Shop shopById(@Param("bookId") Long bookId);
}
