package com.ub.linkedin.routes;

import com.ub.core.base.routes.BaseRoutes;

/**
 * Created by Eduard on 02.10.2015.
 */
public class LinkedinRoutes {

    public static final String AUTH_URL = "https://www.linkedin.com/uas/oauth2/authorization";
    public static final String TOKEN_URL = "https://www.linkedin.com/uas/oauth2/accessToken";
    public static final String GET_USER_URL = "https://www.linkedin.com/v1/people/~:(id,first-name,last-name,email-address,picture-url,site-standard-profile-request)?format=json";

    private static final String ROOT = BaseRoutes.ADMIN + "/linkedin";
    public static final String EDIT = ROOT + "/appProperties/edit";
}
