package com.rcore.commons.utils;

public class StringUtils {

    public static boolean hasText(String s) {
        return s != null && s.length() != 0;
    }

    public static Long getDigits(String s) {
        if (!hasText(s)) return null;
        return Long.parseLong(s.replaceAll("\\D", ""));
    }

    public static String camelCaseToUnderscores(String camel) {
        var underscore = String.valueOf(Character.toLowerCase(camel.charAt(0)));
        for (int i = 1; i < camel.length(); i++) {
            underscore += Character.isLowerCase(camel.charAt(i)) ? String.valueOf(camel.charAt(i))
                    : "_" + String.valueOf(Character.toLowerCase(camel.charAt(i)));
        }
        return underscore.toUpperCase();
    }

}
