package com.spring.mongo.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 注释此类将被映射到数据库的一个集合（collection为集合名称）
 *
 * @author xuweizhi
 * @since 2019-08-25
 */
@Document(collection = "ex_cat")
//创建联合索引
//@CompoundIndexes({
//        //联合索引 name 索引名称 、def 索引字段、number 升序、createTime 降序
//        @CompoundIndex(name = "compound_index", def = "{'number': 1, 'createTime': -1}")
//})
public class Cat implements Serializable {

    /**
     * 标记id字段
     */
    @Id
    private ObjectId id;

    /**
     * 创建单字段索引（默认ASCENDING 升序、DESCENDING 降序）
     */
    //@Indexed(direction = IndexDirection.DESCENDING)
    private Long number;

    /**
     * 修改映射到数据库中的名称
     */
    @Field("change_field")
    private String changeParam;

    private Date createTime;

    private Integer age;

    /**
     * 关联其他集合（不添加此注释时List将会保存具体的实体值，而添加了此注释List保存的是关联集合的id）
     */
    @DBRef
    private List<Dog> dogs;

    /**
     * 此字段不映射到数据库
     */
    @Transient
    private Integer noReflect;

    public Cat() {

    }

    /**
     * 声明构造函数，用于实例化查询结果数据
     */
    @PersistenceConstructor
    public Cat(Long number, String changeParam, Date createTime, Integer age, List<Dog> dogs) {
        this.number = number;
        this.changeParam = changeParam;
        this.createTime = createTime;
        this.age = age;
        this.dogs = dogs;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getChangeParam() {
        return changeParam;
    }

    public void setChangeParam(String changeParam) {
        this.changeParam = changeParam;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Dog> getDogs() {
        return dogs;
    }

    public void setDogs(List<Dog> dogs) {
        this.dogs = dogs;
    }

    public Integer getNoReflect() {
        return noReflect;
    }

    public void setNoReflect(Integer noReflect) {
        this.noReflect = noReflect;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "id=" + id +
                ", parameter1=" + number +
                ", parameter2='" + changeParam + '\'' +
                ", parameter3=" + createTime +
                ", parameter4=" + age +
                ", dogs=" + dogs +
                ", parameter6=" + noReflect +
                '}';
    }

}
