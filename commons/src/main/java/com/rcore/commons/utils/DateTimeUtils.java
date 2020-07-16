package com.rcore.commons.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;

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

    public static DateTimeFormatter isoDateTimeFormatter() {
        return DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");
    }

    public static DateTimeFormatter timeFormatter() {
        return DateTimeFormatter.ofPattern("HH:mm");
    }

    public static String formatWithTimeZone(LocalDateTime dateTime, DateTimeFormatter dateTimeFormatter , String tz) {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(dateTime, ZoneOffset.systemDefault());
        Instant instant = dateTime.toInstant(zonedDateTime.getOffset());
        return dateTimeFormatter.format(LocalDateTime.ofInstant(instant, ZoneId.of(tz)));
    }
}
