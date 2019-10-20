package com.spring.demo.untils;


import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.jetbrains.annotations.Contract;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 封装的 json 解析工具类，提供泛型参数
 *
 * @author xuweizhi
 * @since 2019-09-25
 */
public class JsonUtils {

    private static class SinglePojo {
        private static Gson gson = new Gson();
    }

    @Contract(pure = true)
    public static Gson getInstance() {
        return SinglePojo.gson;
    }

    /**
     * 将Json数据解析成相应的映射对象
     */
    public static <T> T parseJsonObject(String json, Class<T> type) {
        return getInstance().fromJson(json, type);
    }

    /**
     * 将Json数组解析成相应的映射对象列表
     */
    @Contract("null -> null")
    public static <T> List<T> parseJsonList(String json, Class<T> type) {
        //return  getInstance().fromJson(jsonData, new TypeToken<List<T>>() {
        //}.getType());
        return new Gson().fromJson(json, new ParameterizedTypeImpl(type));
    }

    /**
     * 哈哈
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public <T> List<T> parseListByFastJson(String json, Class<T> type) {
        return JSONObject.parseArray(json, type);
    }


    private static class ParameterizedTypeImpl implements ParameterizedType {

        Class clazz;

        @Contract(pure = true)
        public ParameterizedTypeImpl(Class clz) {
            clazz = clz;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{clazz};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
    }
}

