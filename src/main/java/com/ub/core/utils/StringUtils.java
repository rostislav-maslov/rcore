package com.ub.core.utils;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Created with IntelliJ IDEA.
 * User: Anton
 * Date: 1/21/14
 * Time: 5:49 AM
 * To change this template use File | Settings | File Templates.
 */
public class StringUtils {
    public final static String getMD5ofString(String str){
        return DigestUtils.md5Hex(str);

    }
}
