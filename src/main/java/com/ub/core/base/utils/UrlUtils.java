package com.ub.core.base.utils;

import org.apache.commons.codec.binary.Base64;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class UrlUtils {

    public static String urlFromString(String str){
        return StringUtils.cyrillicToLatin(str).replaceAll(" ", "-").replaceAll(" ", "-").replaceAll(" ", "-").toLowerCase();
    }

    public static String longToBase64(Long aLong) {
        return Base64.encodeBase64URLSafeString(aLong.toString().getBytes());
    }

    public static Long base64ToObjectId(String stringId) {
        return Long.valueOf(new String(Base64.decodeBase64(stringId)));
    }

    public static String serverName() {
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        String url = attr.getRequest().getServerName();
        int port = attr.getRequest().getServerPort();
        if (port != 80) {
            url += ":" + String.valueOf(port);
        }
        return url;
    }

    public static String getAbsUrl(String url) {
        String pre = "http://" + serverName();

        return pre + url;
    }
}
