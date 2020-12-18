package com.rcore.rest.api.spring.commons.utils;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class WebRequestUtils {

    private static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
    }

    public static String getHeader(String header) {
        return getRequest().getHeader(header);
    }

    public static String getIp() {
        final String[] ip = new String[1];
        //IP candidates
        Arrays.asList("X-Forwarded-For",
                "Proxy-Client-IP",
                "WL-Proxy-Client-IP",
                "HTTP_X_FORWARDED_FOR",
                "HTTP_X_FORWARDED",
                "HTTP_X_CLUSTER_CLIENT_IP",
                "HTTP_CLIENT_IP",
                "HTTP_FORWARDED_FOR",
                "HTTP_FORWARDED",
                "HTTP_VIA",
                "REMOTE_ADDR")
                .forEach(header -> {
                    String ipFromHeader = getHeader(header);
                    if (StringUtils.hasText(ipFromHeader)) {
                        ip[0] = ipFromHeader.split(",")[0];
                        return;
                    }

                });

        return StringUtils.hasText(ip[0]) ? ip[0] : getRequest().getRemoteAddr();
    }
}
