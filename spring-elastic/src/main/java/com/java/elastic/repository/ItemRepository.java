package com.java.elastic.repository;

import com.java.elastic.entity.Item;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author xuweizhi
 * @since 2019/07/16 16:27
 */
public interface ItemRepository extends ElasticsearchRepository<Item, Long> {

}
