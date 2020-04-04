package com.ub.twitter.routes;

import com.ub.core.base.routes.BaseRoutes;

/**
 * Created by Eduard on 02.10.2015.
 */
public class TwitterRoutes {
    public static final String AUTH_URL = "https://accounts.google.com/o/oauth2/auth";
    public static final String TOKEN_URL = "https://accounts.google.com/o/oauth2/token";

    private static final String ROOT = BaseRoutes.ADMIN + "/twitter";
    public static final String EDIT = ROOT + "/appProperties/edit";
}
