package com.spring.cache.dao;

import com.spring.cache.model.Book;

/**
 * @author xuweizhi
 */
@SuppressWarnings("all")
public interface BookRepository {

    Book getByIsbn(String isbn);

}
