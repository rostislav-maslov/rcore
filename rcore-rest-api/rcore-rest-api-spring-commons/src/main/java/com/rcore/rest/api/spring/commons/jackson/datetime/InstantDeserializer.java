package com.rcore.rest.api.spring.commons.jackson.datetime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
public class InstantDeserializer extends JsonDeserializer<Instant> {

    @Override
    public Instant deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String str = p.getText();
        List<DateTimeFormatter> availableFormatters = Arrays.asList(
                DateTimeFormatter.ISO_LOCAL_DATE_TIME,
                DateTimeFormatter.ISO_DATE,
                DateTimeFormatter.ISO_DATE_TIME,
                DateTimeFormatter.BASIC_ISO_DATE,
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS+ZZZZ"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
        );
        AtomicReference<Instant> localDateTime = new AtomicReference<>();
        availableFormatters
                .forEach(dateTimeFormatter -> {
                    try {
                        localDateTime.set(LocalDateTime.parse(str, dateTimeFormatter).toInstant(ZoneOffset.UTC));
                        return;
                    } catch (Exception e) {
                        log.debug("Local date time deserialize exception", e);
                    }
                });

        if (localDateTime.get() != null)
            return localDateTime.get();

        throw new IOException("Local date time deserialize exception");
    }
}
