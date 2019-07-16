package com.java.elastic.controller;

import com.java.elastic.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xuweizhi
 * @since 2019/07/16 15:55
 */
@RestController
@RequestMapping("/elastic")
public class ElasticController {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @GetMapping("/create")
    public void createIndex() {
        elasticsearchTemplate.createIndex(Item.class);
    }

    @GetMapping("/delete")
    public void deleteIndex() {
        elasticsearchTemplate.deleteIndex(Item.class);
    }
}
