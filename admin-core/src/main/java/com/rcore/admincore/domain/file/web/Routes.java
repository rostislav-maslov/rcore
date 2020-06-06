package com.rcore.admincore.domain.file.web;

import com.rcore.restapi.routes.BaseApiRoutes;

public class Routes {
    public static final String ROOT = BaseApiRoutes.V1 + "/file";
    public static final String BY_ID = ROOT + "/{id}";
}
