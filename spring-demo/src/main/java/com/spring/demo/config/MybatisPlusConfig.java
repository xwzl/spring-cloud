package com.spring.demo.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
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

    /**
     * sql时长分析
     *
     * @return
     */
    @Bean
    public PerformanceInterceptor performanceInterceptor() {
        //可以加入@Profile() 在不同环境打印
        return new PerformanceInterceptor();
    }

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
