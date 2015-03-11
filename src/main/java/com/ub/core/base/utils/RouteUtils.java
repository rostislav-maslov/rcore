package com.ub.core.base.utils;

import com.ub.core.base.httpResponse.ResourceNotFoundException;

public class RouteUtils {

    @Deprecated
    public static String redirectTo404() {
        throw new ResourceNotFoundException();
    }

    public static String redirectTo500() {
        return redirectTo("/500");
    }

    public static String redirectTo(String url) {
        return "redirect:" + url;
    }

}
