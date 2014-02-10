package com.ub.core.base.utils;

public class RouteUtils {

    public static String redirectTo404() {
        return redirectTo("/404");
    }

    public static String redirectTo500() {
        return redirectTo("/500");
    }

    public static String redirectTo(String url) {
        return "redirect:" + url;
    }

}
