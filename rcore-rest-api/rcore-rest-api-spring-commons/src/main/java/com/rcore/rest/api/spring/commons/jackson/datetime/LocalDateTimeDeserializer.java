package com.rcore.rest.api.spring.commons.jackson.datetime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Вова - не удаляй, нужна обратная совместимость
 */
public class LocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime> {
    private static final Logger log = LoggerFactory.getLogger(LocalDateTimeDeserializer.class);

    public LocalDateTimeDeserializer() {
    }

    public LocalDateTime deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        String str = p.getText();
        List<DateTimeFormatter> availableFormatters = Arrays.asList(DateTimeFormatter.ISO_LOCAL_DATE_TIME, DateTimeFormatter.ISO_DATE, DateTimeFormatter.ISO_DATE_TIME, DateTimeFormatter.BASIC_ISO_DATE, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS+ZZZZ"), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSZ"), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS"), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"));
        AtomicReference<LocalDateTime> localDateTime = new AtomicReference();
        availableFormatters.forEach((dateTimeFormatter) -> {
            try {
                localDateTime.set(LocalDateTime.parse(str, dateTimeFormatter));
            } catch (Exception var4) {
                log.debug("Local date time deserialize exception", var4);
            }
        });
        if (localDateTime.get() != null) {
            return (LocalDateTime)localDateTime.get();
        } else {
            throw new IOException("Local date time deserialize exception");
        }
    }
}
