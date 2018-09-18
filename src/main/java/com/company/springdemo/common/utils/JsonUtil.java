package com.company.springdemo.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Preconditions;

import java.io.IOException;
import java.io.Reader;

/**
 * @Auther: whs
 * @Date: 2018/7/19 15:17
 * @Description: Jackson数据类型转换工具
 */
public class JsonUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();
    static {
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }
    public JsonUtil(ObjectMapper mapper) {
        this.objectMapper = mapper;
    }
    
    public static String toString(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }
    
    /**
     * JSON串转换为Java泛型对象，可以是各种类型。
     * @param <T>
     * @param jsonString JSON字符串
     * @param tr         TypeReference,例如: new TypeReference< List<FamousUser> >(){}
     * @return List对象列表
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
	public static <T> T decodeJson(String jsonString, TypeReference<T> tr) throws IOException {
        if (jsonString == null || "".equals(jsonString)) {
            return null;
        } else {
            return (T) objectMapper.readValue(jsonString, tr);
        }
    }

    public static <T> T decodeJson(String jsonString, Class<T> clazz) throws IOException {
        if (jsonString == null || "".equals(jsonString)) {
            return null;
        } else {
            return objectMapper.readValue(jsonString, clazz);
        }
    }
    
    public <T> T readValue(String json, Class<T> type) {
        try {
            return objectMapper.readValue(json, type);
        } catch (IOException e) {
            throw new RuntimeException("jackson parse error :" + json.substring(0, Math.min(100, json.length())), e);
        }
    }

    public <T> T readValue(Reader reader, Class<T> type) throws IOException {
        Preconditions.checkNotNull(reader);
        try {
            return objectMapper.readValue(reader, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("jackson parse error.", e);
        }
    }

    @SuppressWarnings("unchecked")
	public <T> T readValue(String json, TypeReference<T> type) {
        try {
            return (T) objectMapper.readValue(json, type);
        } catch (Exception e) {
            throw new RuntimeException("jackson parse error.", e);
        }
    }

    @SuppressWarnings("unchecked")
	public <T> T readValue(Reader reader, TypeReference<T> type) throws IOException {
        Preconditions.checkNotNull(reader);
        try {
            return (T) objectMapper.readValue(reader, type);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("jackson parse error.", e);
        }
    }
}
