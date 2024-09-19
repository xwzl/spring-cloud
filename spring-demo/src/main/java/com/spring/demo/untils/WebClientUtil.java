package com.spring.demo.untils;

import org.apache.commons.collections4.MapUtils;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

/**
 * @author xuweizhi
 */
@Component
public class WebClientUtil {

    private final MediaType APPLICATION_JSON = MediaType.APPLICATION_JSON;

    private final MediaType APPLICATION_FORM_URLENCODED = MediaType.APPLICATION_FORM_URLENCODED;

    private WebClient webClient;

    private WebClient.Builder builder;

    private String url;


    public WebClientUtil(WebClient.Builder builder) {
        this.builder = builder;
    }


    /**
     * @param parameter  请求参数
     * @param url        请求路径
     * @param resultType 返回结果类型
     * @return 返回值
     */
    public <T> T post(String url, Object parameter, Class<T> resultType) {
        return post(uri(url, HttpMethod.POST), parameter, resultType);
    }

    /**
     * @param parameter  请求参数
     * @param url        请求路径
     * @param header     请求头
     * @param resultType 返回结果类型
     * @return 返回值
     */
    public <T> T post(String url, Object parameter, Map<String, String> header, Class<T> resultType) {
        WebClient.RequestBodySpec uri = uri(url, HttpMethod.POST);
        addHeader(header, uri);
        return post(uri, parameter, resultType);
    }

    private <T> T post(WebClient.RequestBodySpec uri, Object parameter, Class<T> resultType) {

        return uri.contentType(APPLICATION_JSON).body(Mono.just(parameter), Object.class).retrieve().bodyToMono(resultType).block();
    }

    /**
     * @param parameter  请求参数
     * @param url        请求路径
     * @param header     请求头
     * @param resultType 返回结果类型
     * @return 返回值
     */
    public <T> T postForm(String url, Map<String, String> parameter, Map<String, String> header, Class<T> resultType) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.setAll(parameter);
        WebClient.RequestBodySpec uri = uri(url, HttpMethod.POST);
        addHeader(header, uri);
        return postForm(uri, formData, resultType);
    }

    private <T> T postForm(WebClient.RequestBodySpec uri, MultiValueMap<String, String> formData, Class<T> resultType) {

        return uri.contentType(APPLICATION_FORM_URLENCODED).body(BodyInserters.fromFormData(formData)).retrieve().bodyToMono(resultType).block();
    }

    /**
     * @param url        请求路径
     * @param resultType 返回结果类型
     * @return 返回值
     */
    public <T> T get(String url, Class<T> resultType) {

        return uri(url, HttpMethod.GET).retrieve().bodyToMono(resultType).block();
    }

    /**
     * @param url        请求路径
     * @param header     请求头
     * @param resultType 返回结果类型
     * @return 返回值
     */
    public <T> T get(String url, Map<String, String> header, Class<T> resultType) {

        WebClient.RequestBodySpec uri = uri(url, HttpMethod.GET);
        addHeader(header, uri);
        return uri.retrieve().bodyToMono(resultType).block();
    }

    private WebClient.RequestBodySpec uri(String url, HttpMethod method) {
        if (!url.startsWith("/")) {
            url = "/" + url;
        }
        if (webClient == null) {
            // synchronized (this) {
                // String cacheValue = systemConfigCacheWrapper.getCacheValue(SystemConstant.HIS_URL);
                // webClient = this.builder.baseUrl(cacheValue).build();
                // this.url = cacheValue;
            // }
        }
        return webClient.method(method).uri(this.url + url);
    }

    private void addHeader(Map<String, String> header, WebClient.RequestBodySpec uri) {
        if (!MapUtils.isEmpty(header)) {
            header.forEach(uri::header);
        }
    }

}