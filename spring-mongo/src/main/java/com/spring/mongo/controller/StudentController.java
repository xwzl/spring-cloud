package com.spring.mongo.controller;

import com.spring.mongo.model.Student;
import io.swagger.annotations.Api;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Student mongodb test
 *
 * @author xuweizhi
 * @since 2020/05/07 14:36
 */
@Api("MongoDB 测试视图层")
@RestController
@RequestMapping("student")
public class StudentController {

    @Resource
    private MongoTemplate mongoTemplate;

    @GetMapping("list")
    public List<Student> list() {
        mongoTemplate.find(Query.query(Criteria.where("")), String.class);
        return null;
    }
}
