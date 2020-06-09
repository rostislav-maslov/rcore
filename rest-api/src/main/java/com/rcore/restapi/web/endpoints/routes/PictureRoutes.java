package com.rcore.restapi.web.endpoints.routes;

import com.rcore.restapi.routes.BaseNotSecureApiRoutes;

public class PictureRoutes {
    public static final String ROOT = BaseNotSecureApiRoutes.V1 + "/picture";
    public static final String BY_ID = ROOT + "/{id}";
    public static final String WIDTH = BY_ID + "/width";
    public static final String UPLOAD = ROOT + "/upload";
}
