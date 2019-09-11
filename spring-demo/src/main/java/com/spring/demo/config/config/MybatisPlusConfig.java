package com.spring.demo.config.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.spring.demo.config.handler.MyMetaObjectHandler;
import org.springframework.context.annotation.Bean;

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
        //return new MetaObjectHandler() {
        //    @Override
        //    public void insertFill(MetaObject metaObject) {
        //        this.setInsertFieldValByName("updateTime", new Date(), metaObject);
        //    }
        //
        //    @Override
        //    public void updateFill(MetaObject metaObject) {
        //        this.setUpdateFieldValByName("updateTime", new Date(), metaObject);
        //    }
        //};
        return new MyMetaObjectHandler();
    }

}
