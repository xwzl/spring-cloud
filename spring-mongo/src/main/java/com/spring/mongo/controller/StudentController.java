package com.spring.mongo.controller;

import com.spring.mongo.model.LocalDO;
import com.spring.mongo.model.Student;
import io.swagger.annotations.Api;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
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

    @PostMapping("save")
    public void save() {
        LocalDO local1 = new LocalDO();
        // 天府广场
        GeoJsonPoint point1 = new GeoJsonPoint(104.0642175, 30.65832306);
        local1.setLocation(point1);
        LocalDO local2 = new LocalDO();
        GeoJsonPoint point2 = new GeoJsonPoint(104.0655403, 30.66700944);
        local2.setLocation(point1);
        LocalDO local3 = new LocalDO();
        GeoJsonPoint point3 = new GeoJsonPoint(104.0679806, 30.67251);
        local3.setLocation(point1);
        LocalDO local4 = new LocalDO();
        GeoJsonPoint point4 = new GeoJsonPoint(104.0658819, 30.70175056);
        local4.setLocation(point1);
        LocalDO local5 = new LocalDO();
        GeoJsonPoint point5 = new GeoJsonPoint(104.062783, 30.546587);
        local5.setLocation(point5);
        List<LocalDO> list = new ArrayList<>();
        mongoTemplate.insert(list, LocalDO.class);
    }

    @GetMapping("test")
    public void test(){
        GeoJsonPoint point5 = new GeoJsonPoint(104.062783, 30.546587);
        Query query = Query.query(new Criteria().nearSphere(point5));
        List<LocalDO> list = mongoTemplate.find(query, LocalDO.class);
        //Double a2 = Double.valueOf(param.getLat());
        //Double a1 = Double.valueOf(param.getLng());
        //for (ContentDO contentDO : location) {
        //    Double a3 = Double.valueOf(contentDO.getLng());
        //    Double a4 = Double.valueOf(contentDO.getLat());
        //    double distance = rad.getDistance(a1, a2, a3, a4);
        //    // double v = rad.googleDistance(a1, a2, a3, a4);
        //    // double v1 = rad.distanceOne(a4, a2, a3, a1);
        //    // double v2 = rad.distanceTwo(a2, a1, a4, a3);
        //    // double v3 = rad.distanceThree(a1, a2, a3, a4);
        //    System.out.println(distance + ":" + contentDO.getId().toString());
        //    contentDO.setDistance(distance + "");
        //}
    }
}
