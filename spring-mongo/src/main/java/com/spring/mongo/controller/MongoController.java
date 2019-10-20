package com.spring.mongo.controller;

import com.spring.mongo.model.Cat;
import com.spring.mongo.model.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xuweizhi
 * @since 2019/08/28 16:32
 */
@RestController
public class MongoController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @GetMapping(value = "/")
    public String index() {
        return "redirect:/find";
    }

    /**
     * 新增文档
     */
    @GetMapping(value = "/insert")
    public void insert() {

        //insert方法并不提供级联类的保存，所以级联类需要先自己先保存
        Dog dog = new Dog(1000L);
        //执行完insert后对象entityTest1将会获得保存后的id
        mongoTemplate.insert(dog);
        //再添加一条
        Dog dog1 = new Dog(1001L);
        mongoTemplate.insert(dog1);

        //创建列表并将保存后的关联对象添加进去
        ArrayList<Dog> entityTest1List = new ArrayList<Dog>();
        entityTest1List.add(dog);
        entityTest1List.add(dog1);

        //新增主体对象
        Cat entityTest = new Cat(100L, "test", new Date(), 10, entityTest1List);
        //新增数据的主键已经存在，则会抛DuplicateKeyException异常
        mongoTemplate.insert(entityTest);
    }

    /**
     * 保存文档
     * 保存与新增的主要区别在于，如果主键已经存在，新增抛出异常，保存修改数据
     */
    @GetMapping(value = "/save")
    public void save() {

        //查询最后一条数据并更新
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        Cat cat = mongoTemplate.findOne(Query.query(Criteria.where("")).with(sort), Cat.class);
        cat.setAge(3000);

        //保存数据的主键已经存在，则会对已经存在的数据修改
        mongoTemplate.save(cat);
    }

    /**
     * 删除文档
     */
    @GetMapping(value = "/delete")
    public void delete() {

        //查询第一条数据并删除
        Cat cat = mongoTemplate.findOne(Query.query(Criteria.where("")), Cat.class);

        //remove方法不支持级联删除所以要单独删除子数据
        List<Dog> lists = cat.getDogs();
        for (Dog dog : lists) {
            mongoTemplate.remove(dog);
        }

        //删除主数据
        mongoTemplate.remove(cat);

    }

    /**
     * 更新文档
     */
    @GetMapping(value = "/update")
    public void update() {
        //将查询条件符合的全部文档更新
        Query query = new Query();
        Update update = Update.update("changeParam_", "update");
        mongoTemplate.updateMulti(query, update, Cat.class);
    }

    /**
     * 查询文档
     */
    @GetMapping(value = "/find")
    public List<Cat> find(Model model) {
        //查询小于当前时间的数据，并按时间倒序排列
        Sort sort = Sort.by(Sort.Direction.DESC, "createTime");
        List<Cat> findTestList = mongoTemplate.find(Query.query(Criteria.where("createTime").lt(new Date())).with(sort), Cat.class);
        model.addAttribute("findTestList", findTestList);

        //使用findOne查询如果结果极为多条，则返回排序在最上面的一条
        Cat findOneTest = mongoTemplate.findOne(Query.query(Criteria.where("createTime").lt(new Date())).with(sort), Cat.class);
        model.addAttribute("findOneTest", findOneTest);

        //模糊查询
        List<Cat> findTestList1 = mongoTemplate.find(Query.query(Criteria.where("createTime").lt(new Date()).and("parameter2").regex("es")), Cat.class);
        model.addAttribute("findTestList1", findTestList1);

        //分页查询（每页3行第2页）
        Pageable pageable = PageRequest.of(1, 3, sort);
        List<Cat> findTestList2 = mongoTemplate.find(Query.query(Criteria.where("createTime").lt(new Date())).with(pageable), Cat.class);
        //共多少条
        long count = mongoTemplate.count(Query.query(Criteria.where("createTime").lt(new Date())), Cat.class);
        //返回分页对象
        Page<Cat> page = new PageImpl<Cat>(findTestList2, pageable, count);
        model.addAttribute("page", page);

        //分页查询（通过起始行和数量也可以自己实现分页逻辑）
        List<Cat> findTestList3 = mongoTemplate.find(Query.query(Criteria.where("createTime").lt(new Date())).with(sort).skip(3).limit(3), Cat.class);
        model.addAttribute("findTestList3", findTestList3);

        return findTestList3;
    }
}
