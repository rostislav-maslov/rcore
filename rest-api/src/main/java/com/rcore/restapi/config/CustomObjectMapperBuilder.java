package com.rcore.restapi.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.rcore.restapi.config.jackson.LocalDateTimeDeserializer;
import com.rcore.restapi.config.jackson.LocalDateTimeSerializer;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class CustomObjectMapperBuilder {

    public Jackson2ObjectMapperBuilder getJackson2ObjectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        builder.serializerByType(LocalDateTime.class, new LocalDateTimeSerializer());
        builder.serializationInclusion(JsonInclude.Include.NON_NULL);
        builder.deserializerByType(LocalDateTime.class, new LocalDateTimeDeserializer());
        return builder;
    }
}
