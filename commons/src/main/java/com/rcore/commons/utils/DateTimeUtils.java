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

    public static String formatFromUtcToTimeZone(LocalDateTime dateTime, DateTimeFormatter dateTimeFormatter , String tz) {
        Instant instant = dateTime.toInstant(ZoneOffset.UTC);
        return dateTimeFormatter.format(LocalDateTime.ofInstant(instant, ZoneId.of(tz)));
    }

    public static LocalDateTime dateTimeFromUtcToTimeZone(LocalDateTime localDateTime, String tz) {
        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
        return LocalDateTime.ofInstant(instant, ZoneId.of(tz));
    }

    public static LocalDateTime dateTimeToTimeZone(LocalDateTime localDateTime, String tz) {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneOffset.systemDefault());
        Instant instant = localDateTime.toInstant(zonedDateTime.getOffset());
        return LocalDateTime.ofInstant(instant, ZoneId.of(tz));
    }

    public static LocalDateTime dateTimeFromTimeZoneToTimeZone(LocalDateTime localDateTime, String fromTz, String toTz) {
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of(fromTz));
        Instant instant = localDateTime.toInstant(zonedDateTime.getOffset());
        return LocalDateTime.ofInstant(instant, ZoneId.of(toTz));
    }
}
