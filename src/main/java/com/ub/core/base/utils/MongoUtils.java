package com.ub.core.base.utils;

public class MongoUtils {
    private static final String DOT_EXCAPE = "%DOT%";

    public static final String dotMapKeyToEscape(String key){
        return key.replaceAll("\\.",DOT_EXCAPE);
    }
    public static final String escapeToDotMapKey(String key){
        return key.replaceAll(DOT_EXCAPE,".");
    }
}
