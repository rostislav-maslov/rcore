package com.rcore.restapi.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JsonUtils {

    public static Map<String, Object> toMap(Object request) {
        return new ObjectMapper().convertValue(request, new TypeReference<Map<String, Object>>() {
        });
    }

    public static String toString(Object request) {
        try {
            return new ObjectMapper().writeValueAsString(request);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Parse error : " + e.getMessage();
        }
    }
}