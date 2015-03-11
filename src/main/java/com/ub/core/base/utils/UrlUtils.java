package com.ub.core.base.utils;

import org.apache.commons.codec.binary.Base64;

public class UrlUtils {
    public static String longToBase64(Long aLong){
        return Base64.encodeBase64URLSafeString(aLong.toString().getBytes());
    }

    public static Long base64ToObjectId(String stringId){
        return Long.valueOf(new String( Base64.decodeBase64(stringId)));
    }
}
