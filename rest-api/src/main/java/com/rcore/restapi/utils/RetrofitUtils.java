package com.rcore.restapi.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import retrofit2.Response;

public class RetrofitUtils {

    public static String getStringRequest(Response response) {
        return response.raw().request().url().toString();
    }

    public static String getStringResponse(Response response) {
        try {
            return new ObjectMapper().writeValueAsString(response.body());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return "Parse error: " + e.getMessage();
        }
    }

}
