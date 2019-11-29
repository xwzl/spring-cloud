package com.java.elastic.repository;

import com.java.elastic.entity.EsCityEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * ES城市 Repository
 *
 * @author xuweizhi
 * @since 2019-11-29
 */
@Repository
public interface EsCityRepository extends ElasticsearchRepository<EsCityEntity, Long> {
}
