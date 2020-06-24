package com.spring.mongo.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import javax.annotation.Resource;

@Configuration
public class MongoConfig {

    @Resource
    private MongoDatabaseFactory mongoDatabaseFactory;

    @Resource
    private MongoMappingContext mongoMappingContext;

    @Bean
    public MappingMongoConverter mappingMongoConverter() {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDatabaseFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
        //此处是去除插入数据库的 _class 字段
        converter.setTypeMapper(new DefaultMongoTypeMapper(null));
        return converter;
    }
}

