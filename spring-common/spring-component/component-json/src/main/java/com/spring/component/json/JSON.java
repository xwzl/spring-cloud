package com.spring.component.json;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xuweizhi
 */
public class JSON {

    private static ObjectMapper mapper;

    public JSON(ObjectMapper objectMapper) {
        mapper = objectMapper;
    }

    public static <T> T fromJson(String json, Class<T> type) {
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static <T> T fromJson(String json, TypeReference<T> typeReference) {
        try {
            return mapper.readValue(json, typeReference);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static <T> List<T> fromJsonToArray(String json, Class<T> type) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructArrayType(type));
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static <K, V> Map<K, V> fromJsonToMap(String json, Class<K> keyType, Class<V> valueType) {
        try {
            return mapper.readValue(json, mapper.getTypeFactory().constructMapType(HashMap.class, keyType, valueType));
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static JsonNode fromJson(String json) {
        try {
            return mapper.readTree(json);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

    public static ObjectMapper getMapper() {
        return mapper;
    }

    public static <T> String toJsonString(T object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new JsonException(e);
        }
    }

}
