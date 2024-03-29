// package com.spring.demo.config;
//
// import io.swagger.annotations.ApiOperation;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import springfox.documentation.builders.ApiInfoBuilder;
// import springfox.documentation.builders.PathSelectors;
// import springfox.documentation.builders.RequestHandlerSelectors;
// import springfox.documentation.service.ApiInfo;
// import springfox.documentation.spi.DocumentationType;
// import springfox.documentation.spring.web.plugins.Docket;
// import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
//
// /**
//  * https://doc.xiaominfo.com/knife4j/documentation/
//  *
//  * @author xuweizhi
//  */
// @Configuration
// @EnableSwagger2WebMvc
// public class Knife4jConfiguration {
//
//     @Bean(value = "defaultApi")
//     public Docket defaultApi() {
//         return new Docket(DocumentationType.SWAGGER_2)
//                 .apiInfo(apiInfo())
//                 .groupName("默认接口文档")
//                 .select()
//                 //.apis(RequestHandlerSelectors.basePackage("com.swagger.bootstrap.ui.demo.controller"))
//                 .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
//                 .paths(PathSelectors.any())
//                 .build()
//                 .securitySchemes(null);
//     }
//
//
//     private ApiInfo apiInfo() {
//         return new ApiInfoBuilder()
//                 //.title("swagger-bootstrap-ui-demo RESTful APIs")
//                 .description("# swagger-bootstrap-ui-demo RESTful APIs")
//                 .termsOfServiceUrl("http://www.xx.com/")
//                 .version("1.0")
//                 .build();
//     }
//
// }
