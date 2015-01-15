package com.ub.core.base.utils;

import org.apache.commons.codec.binary.Base64;
import org.bson.types.ObjectId;

public class ObjectIdUtils {

    public static String objectIdToBase64(ObjectId id){
        return Base64.encodeBase64URLSafeString(id.toByteArray());
    }

    public static ObjectId base64ToObjectId(String stringId){
        return new ObjectId( Base64.decodeBase64(stringId));
    }
}
