package com.ub.core.base.utils;

public class StringUtils {

    public static boolean isEmpty(String s){
        if(s == null || s.equals(""))
            return true;
        return false;
    }

    public static boolean isNotEmpty(String s){
        return !isEmpty(s);
    }

    public static String catString(String str, Integer l){
        if(str == null) return null;
        if(str.length() <= l) return str;

        return str.substring(0, l-1);
    }
    public static String catStringWithDot(String str, Integer l){
        if(str == null) return null;
        if(str.length() <= l) return str;

        return str.substring(0, l-4) + "...";
    }
}
