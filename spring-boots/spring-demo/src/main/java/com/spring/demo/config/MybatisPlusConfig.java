package com.spring.demo.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;

import java.util.Date;

/**
 * @author xuweizhi
 * @since 2019-08-07
 */
//@EnableTransactionManagement
//@Configuration
//@MapperScan("*")
public class MybatisPlusConfig {

    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new MetaObjectHandler() {
            @Override
            public void insertFill(MetaObject metaObject) {
                this.setInsertFieldValByName("updateTime", new Date(), metaObject);
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                this.setUpdateFieldValByName("updateTime", new Date(), metaObject);
            }
        };
    }

}
