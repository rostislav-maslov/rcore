package com.rcore.commons.utils;

public class StringUtils {

    public static boolean hasText(String s) {
        if (s == null || s.length() == 0)
            return false;
        return true;
    }

}
