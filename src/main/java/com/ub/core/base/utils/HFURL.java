package com.ub.core.base.utils;

public class HFURL {
    public static String russianToHfu(String text){
        String url = text.toLowerCase();
        url = StringUtils.cyrillicToLatin(url);
        url = url.replaceAll(" ", "-");
        url = url.replaceAll("/.", "-");
        url = url.replaceAll(",","-");
        url = url.replaceAll("\"","-");
        url = url.replaceAll("'","-");
        url = url.replaceAll("<","-");
        url = url.replaceAll(">","-");
        url = url.replaceAll("%","-");
        url = url.replaceAll("#","-");
        url = url.replaceAll("\\?","-");
        url = url.replaceAll("@","-");
        url = url.replaceAll(":","-");
        url = url.replaceAll("«","-");
        url = url.replaceAll("»","-");
        return url;
    }
}
