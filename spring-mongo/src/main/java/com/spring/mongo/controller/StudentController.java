package com.spring.mongo.controller;

import com.spring.mongo.model.LocalDO;
import com.spring.mongo.model.Student;
import com.spring.mongo.utils.Rad;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Student mongodb test
 *
 * @author xuweizhi
 * @since 2020/05/07 14:36
 */
@Slf4j
@RestController
@Api("MongoDB 测试视图层")
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
        local1.setLocationName("天府广场");
        // 骡马市
        LocalDO local2 = new LocalDO();
        GeoJsonPoint point2 = new GeoJsonPoint(104.0655403, 30.66700944);
        local2.setLocation(point2);
        local2.setLocationName("骡马市");
        // 文殊院
        LocalDO local3 = new LocalDO();
        GeoJsonPoint point3 = new GeoJsonPoint(104.0679806, 30.67251);
        local3.setLocation(point3);
        local3.setLocationName("文殊院");
        // 万达广场(金牛区)
        LocalDO local4 = new LocalDO();
        GeoJsonPoint point4 = new GeoJsonPoint(104.0658819, 30.70175056);
        local4.setLocation(point4);
        local4.setLocationName("万达广场(金牛区)");
        // 天府三街
        LocalDO local5 = new LocalDO();
        GeoJsonPoint point5 = new GeoJsonPoint(104.062783, 30.546587);
        local5.setLocation(point5);
        local5.setLocationName("天府三街");
        List<LocalDO> list = new ArrayList<>();
        list.add(local1);
        list.add(local2);
        list.add(local3);
        list.add(local4);
        list.add(local5);
        mongoTemplate.insert(list, LocalDO.class);
    }

    @GetMapping("test")
    public void test() {
        GeoJsonPoint point5 = new GeoJsonPoint(104.062783, 30.546587);
        // 三 D 排序
        Query query = Query.query(Criteria.where("location").nearSphere(point5));
        List<LocalDO> result = mongoTemplate.find(query, LocalDO.class);
        double a2 = 30.546587;
        double a1 = 104.062783;
        Rad rad = new Rad();
        for (LocalDO localDO : result) {
            double a3 = localDO.getLocation().getX();
            double a4 = localDO.getLocation().getY();
            double distance = rad.getDistance(a1, a2, a3, a4);
            log.info(localDO.getLocationName() + ":" + distance);
        }
    }
}
