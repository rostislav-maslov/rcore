package com.rcore.commons.utils;

public class StringUtils {

    public static boolean hasText(String s) {
        return s != null && s.length() != 0;
    }

    public static Long getDigits(String s) {
        if (!hasText(s)) return null;
        return Long.parseLong(s.replaceAll("\\D", ""));
    }

}
