package com.example.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import java.io.IOException;

public class JsonHelper {
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    //定义框架支持目前jdk版本
    static {
        OBJECT_MAPPER
                .registerModule(new ParameterNamesModule())
                .registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule());
    }

    public JsonHelper() {

    }

    //String转Json
    public static String toJSON(Object object) {
        if (object == null) return "{}";

        try {
            String JSON = OBJECT_MAPPER.writeValueAsString(object);
            return JSON;
        } catch (JsonProcessingException e) {
            throw new RuntimeException();
        }
    }

    //Json转字符串
    public static <T> T toBean(String json, Class<T> clazz) {
        try {
            T bean = OBJECT_MAPPER.readValue(json, clazz);
            return bean;
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }

//    public static void main(String[] args) {
//        String json = toJSON(Collections.singletonMap("jackon" , "json"));
//        System.out.println(json);
//    }
//    public static void main(String[] args) {
//        HashMap<String,String> map = toBean("{\"jackon\":\"json\"}", HashMap.class);
//        System.out.println(map);
//    }
}
