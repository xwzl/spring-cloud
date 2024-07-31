package com.blog.patrick.config;

import cn.hutool.core.util.RandomUtil;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springdoc.core.customizers.GlobalOpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Knife4j整合Swagger3 Api接口文档
 * </p>
 *
 * @author Patrick
 * @since 2024-07-02
 */
@Configuration
public class Knife4jConfig {

    /**
     * 根据@Tag 上的排序，写入x-order
     *
     * @return the global open api customizer
     */
    @Bean
    public GlobalOpenApiCustomizer orderGlobalOpenApiCustomizer() {
        return openApi -> {
            if (openApi.getTags()!=null){
                openApi.getTags().forEach(tag -> {
                    Map<String,Object> map=new HashMap<>();
                    map.put("x-order", RandomUtil.randomInt(0,100));
                    tag.setExtensions(map);
                });
            }
            if(openApi.getPaths()!=null){
                openApi.addExtension("x-test123","333");
                openApi.getPaths().addExtension("x-abb",RandomUtil.randomInt(1,100));
            }

        };
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("XXX用户系统API")
                        .version("1.0")

                        .description( "Knife4j集成springdoc-openapi示例")
                        .termsOfService("http://doc.xiaominfo.com")
                        .license(new License().name("Apache 2.0")
                                .url("http://doc.xiaominfo.com")));
    }

//    @Bean
//    public GroupedOpenApi adminApi() { // 创建了一个api接口的分组
//        return GroupedOpenApi.builder()
//                .group("admin-api") // 分组名称
//                .pathsToMatch("/**") // 接口请求路径规则
//                .build();
//    }
//
//    @Bean
//    public OpenAPI openAPI(){
//        return new OpenAPI()
//                .info(new Info() // 基本信息配置
//                        .title("Knife4j整合Swagger3 Api接口文档") // 标题
//                        .description("Knife4j后端接口服务...") // 描述Api接口文档的基本信息
//                        .version("v1.0.0") // 版本
//                        // 设置OpenAPI文档的联系信息，包括联系人姓名为"patrick"，邮箱为"patrick@gmail.com"。
//                        .contact(new Contact().name("patrick").email("patrick@gmail.com"))
//                        // 设置OpenAPI文档的许可证信息，包括许可证名称为"Apache 2.0"，许可证URL为"http://springdoc.org"。
//                        .license(new License().name("Apache 2.0").url("http://springdoc.org"))
//                );
//    }
}

