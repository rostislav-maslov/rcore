package com.rcore.commons.utils;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class DateTimeUtils {

    public static LocalDateTime fromMillis(long millis) {
        return fromMillis(millis, ZoneId.systemDefault().toString());
    }

    public static LocalDateTime fromMillis(long millis, String timeZone) {
        return Instant.ofEpochMilli(millis)
                .atZone(ZoneId.of(timeZone)).toLocalDateTime();
    }

    public static long getMillis(LocalDateTime localDateTime, String timeZone) {
        return ZonedDateTime.of(localDateTime, ZoneId.of(timeZone))
                .toInstant()
                .toEpochMilli();
    }

    public static long getMillis(LocalDateTime localDateTime) {
        return getMillis(localDateTime, ZoneId.systemDefault().toString());
    }

    public static long getNowMillis() {
        return getMillis(LocalDateTime.now());
    }

}
