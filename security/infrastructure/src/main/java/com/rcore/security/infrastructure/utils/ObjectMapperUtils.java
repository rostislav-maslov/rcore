package com.rcore.security.infrastructure.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class ObjectMapperUtils {

    /**
     * ObjectMapper с сериализцией даты в строку
     *
     * @return
     */
    public static ObjectMapper localDateTimeMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule simpleModule = new SimpleModule("DateTimeModule", new Version(1, 0, 0, null));
        simpleModule.addSerializer(LocalDateTime.class, new ToStringSerializer());
        simpleModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        objectMapper.registerModule(simpleModule);
        objectMapper.setDefaultPropertyInclusion(JsonInclude.Include.NON_NULL);
        return objectMapper;
    }

    public static class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {

        @Override
        public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            String str = p.getText();
            try {
                return LocalDateTime.parse(str);
            } catch (DateTimeParseException e) {
                return null;
            }
        }
    }
}
