package com.spring.cache.dao;

import com.spring.cache.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

/**
 * @author xuweizhi
 * @since 2021/08/03 17:28
 */
@Slf4j
@Component
@SuppressWarnings("all")
public class SimpleBookRepository implements BookRepository {

    /**
     * 未加缓存注解，访问效率速度慢
     *
     * @param isbn 编号
     * @return 返回值
     */
    @Override
    @Cacheable("books")
    public Book getByIsbn(String isbn) {
        simulateSlowService();
        return new Book(isbn, "Some book"+isbn);
    }

    private void simulateSlowService() {
        try {
            long time = (long) (Math.random()*3000);
            log.info("stop time:{}", time);
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }

}
