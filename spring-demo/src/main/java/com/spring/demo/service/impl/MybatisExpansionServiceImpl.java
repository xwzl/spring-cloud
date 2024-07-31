package com.spring.demo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.spring.demo.mapper.BookMapper;
import com.spring.demo.model.dos.Book;
import com.spring.demo.model.dos.MybatisExpansion;
import com.spring.demo.mapper.MybatisExpansionMapper;
import com.spring.demo.service.MybatisExpansionService;
import org.springframework.stereotype.Service;

import jakarta.annotation.Resource;
import java.util.List;

/**
 * mybatis plus 扩展测试
 *
 * @author xuweizhi
 * @since 2019-08-07
 */
@Service
public class MybatisExpansionServiceImpl extends ServiceImpl<MybatisExpansionMapper, MybatisExpansion> implements MybatisExpansionService {

    @Resource
    private BookMapper bookMapper;

    @Override
    public void mybatisDemo() {
        List<Book> books = bookMapper.bookList();
        books.stream().forEach(System.out::println);
    }
}
