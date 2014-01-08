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
}
