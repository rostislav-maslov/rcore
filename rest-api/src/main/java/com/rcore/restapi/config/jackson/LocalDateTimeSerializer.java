package com.rcore.restapi.config.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.rcore.commons.utils.DateTimeUtils;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {

    @Override
    public void serialize(LocalDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String s = DateTimeUtils.isoDateTimeFormatter().format(value);
        gen.writeString(s);
    }
}
