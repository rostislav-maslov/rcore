package com.ub.core.picture.routes;

import com.ub.core.base.routes.BaseRoutes;

/**
 * Created by ramaslov on 22.11.14.
 */
public class PicturesAdminRoutes {
    private static final String ROOT = BaseRoutes.ADMIN + "/pictures";

    public final static String ADD = ROOT +"/add";
    public final static String CKEDITOR_ADD = ROOT +"/ckeditor/add";
    public final static String EDIT = ROOT +"/edit";
    public final static String ALL = ROOT +"/all";
    public final static String DELETE = ROOT +"/delete";


}
