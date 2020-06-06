package com.rcore.admincore.domain.user.web;

import com.rcore.restapi.routes.BaseApiRoutes;

public class Routes {
    public static final String ROOT = BaseApiRoutes.V1 + "/user";
    public static final String BY_ID = ROOT + "/{id}";
    public static final String BLOCK = BY_ID + "/block";
    public static final String ACTIVATE = BY_ID + "/activate";
}
