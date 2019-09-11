package com.spring.demo.config.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * RestTemplate 模板配置
 *
 * @author xuweizhi
 * @date 2019/08/22 22:54
 */
@Configuration
public class RestTemplateConfig {

    /**
     * 有且仅有当前 bean 被使用才会加入 IoC 容器中
     *
     * @return RestTemplate
     */
    @Bean
    @ConditionalOnClass
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /*@Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(clientHttpRequestFactory());

        //restTemplate默认的HttpMessageConverter
        List<HttpMessageConverter<?>> messageConverters = restTemplate.getMessageConverters();
        List<HttpMessageConverter<?>> messageConvertersNew = new ArrayList<HttpMessageConverter<?>>();

        for(HttpMessageConverter httpMessageConverter : messageConverters){
            //跳过MappingJackson2HttpMessageConverter
            if (httpMessageConverter instanceof MappingJackson2HttpMessageConverter) continue;

            messageConvertersNew.add(httpMessageConverter);
        }

        //添加fastjson转换器
        messageConvertersNew.add(fastJsonHttpMessageConverter());

        return restTemplate;
    }

    @Bean
    public HttpMessageConverter fastJsonHttpMessageConverter() {
        //MediaType
        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);

        //FastJsonConfig
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.WriteMapNullValue,
                SerializerFeature.QuoteFieldNames);

        //创建FastJsonHttpMessageConverter4    Spring 4.2后使用
        FastJsonHttpMessageConverter4 fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter4();
        fastJsonHttpMessageConverter.setSupportedMediaTypes(mediaTypes);
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

        return fastJsonHttpMessageConverter;
    }

    @Bean
    public RestTemplateCustomizer restTemplateCustomizer(){
        return new RestTemplateCustomizer(){
            @Override
            public void customize(RestTemplate restTemplate) {
                HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

                //创建连接管理器
                PoolingHttpClientConnectionManager poolingHttpClientConnectionManager
                                         = new PoolingHttpClientConnectionManager();
                poolingHttpClientConnectionManager.setMaxTotal(100);
                poolingHttpClientConnectionManager.setDefaultMaxPerRoute(20);
                httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager);

                //创建httpClient
                HttpClient httpClient = httpClientBuilder.build();

                //创建HttpComponentsClientHttpRequestFactory
                HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory
                                             = new HttpComponentsClientHttpRequestFactory(httpClient);
                httpComponentsClientHttpRequestFactory.setConnectTimeout(10 * 1000);
                httpComponentsClientHttpRequestFactory.setReadTimeout(60 * 1000);
                httpComponentsClientHttpRequestFactory.setConnectionRequestTimeout(20 * 1000);

                restTemplate.setRequestFactory(httpComponentsClientHttpRequestFactory);
            }
        };
    }


    @Bean
    public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory() {
        try {
            HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

            //开始设置连接池
            PoolingHttpClientConnectionManager poolingHttpClientConnectionManager
                                                    = new PoolingHttpClientConnectionManager();
            poolingHttpClientConnectionManager.setMaxTotal(100);  //最大连接数
            poolingHttpClientConnectionManager.setDefaultMaxPerRoute(20);  //同路由并发数
            httpClientBuilder.setConnectionManager(poolingHttpClientConnectionManager);

            HttpClient httpClient = httpClientBuilder.build();
            // httpClient连接配置
            HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                                                    = new HttpComponentsClientHttpRequestFactory(httpClient);
            clientHttpRequestFactory.setConnectTimeout(30 * 1000);  //连接超时
            clientHttpRequestFactory.setReadTimeout(60 * 1000);     //数据读取超时时间
            clientHttpRequestFactory.setConnectionRequestTimeout(30 * 1000);  //连接不够用的等待时间
            return clientHttpRequestFactory;
        }
        catch (Exception e) {
            logger.error("初始化clientHttpRequestFactory出错", e);
        }
        return null;
    }

    */

}
