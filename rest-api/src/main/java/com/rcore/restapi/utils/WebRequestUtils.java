package com.rcore.restapi.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import static com.rcore.restapi.headers.WebHeaders.*;

public class WebRequestUtils {

    private static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    public static String getHeader(String header) {
        return getRequest().getHeader(header);
    }

    public static String getAuthToken() {
        return getHeader(X_AUTH_TOKEN);
    }

    public static String getDeviceToken() {
        return getHeader(X_DEVICE_TOKEN);
    }

    public static String getDeviceType() {
        return getHeader(X_DEVICE_TYPE);
    }

    public static String getApplicationVersion() {
        return getHeader(X_APPLICATION_VERSION);
    }

    public static String getDeviceWidth() {
        return getHeader(X_DEVICE_WIDTH);
    }

    //TODO реализовать
    public static String getIp() {
        return "ip";
    }
}
