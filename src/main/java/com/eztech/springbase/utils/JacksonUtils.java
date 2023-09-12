package com.eztech.springbase.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * Json 工具类
 *
 * @author chenqinru
 * @date 2023/09/12
 */
public class JacksonUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static <K, V> Map<K, V> toMap(String json) {
        try {
            return MAPPER.readValue(json, Map.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <K, V> Map<K, V> toMap(String json, TypeReference<Map<K, V>> typeReference) {
        try {
            return MAPPER.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> toList(String json) {
        try {
            return MAPPER.readValue(json, List.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> toList(String json, TypeReference<List<T>> typeReference) {
        try {
            return MAPPER.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            return MAPPER.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> T toObject(String json, TypeReference<T> typeReference) {
        try {
            return MAPPER.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJson(Object object) {
        try {
            return MAPPER.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String toJson(Object object, String pattern) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
            return MAPPER.writer(dateFormat).writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <K, V> Map<K, V> objectToMap(Object object) {
        return MAPPER.convertValue(object, Map.class);
    }

    public static <K, V> Map<K, V> objectToMap(Object object, TypeReference<Map<K, V>> typeReference) {
        return MAPPER.convertValue(object, typeReference);
    }

    public static <T> T mapToObject(Map<?, ?> map, Class<T> clazz) {
        return MAPPER.convertValue(map, clazz);
    }

    public static <T> T mapToObject(Map<?, ?> map, TypeReference<T> typeReference) {
        return MAPPER.convertValue(map, typeReference);
    }
}
