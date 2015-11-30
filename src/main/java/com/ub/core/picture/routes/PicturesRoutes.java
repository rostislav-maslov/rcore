package com.ub.core.picture.routes;

import com.ub.core.base.utils.UrlUtils;
import org.bson.types.ObjectId;

public class PicturesRoutes {
    private static final String ROOT = "/pics";

    public static final String PIC = ROOT +"/{id}";

    public static String PIC(ObjectId id){
        String idS = "";
        if(id != null){
            idS = id.toString();
        }
        return ROOT + "/" + idS;
    }

    public static String PIC_ABS(ObjectId id){
        return UrlUtils.getAbsUrl(PIC(id));
    }
}
