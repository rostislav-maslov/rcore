package com.rcore.restapi.routes;

public class BaseAuthRoutes {
    public static final String INIT_ADMIN = BaseNotSecureApiRoutes.V1 +  "/init-admin";
    public static final String AUTH = BaseNotSecureApiRoutes.V1 + "/auth";
    public static final String LOGIN = AUTH + "/login";
    public static final String LOGOUT = AUTH + "/logout";
    public static final String REFRESH = AUTH + "/refresh";

    public static final String EMAIL = LOGIN + "/email";

}
