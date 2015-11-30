package com.ub.facebook.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AppPropertiesFbDoc {
    public static final String STATIC_ID = "FbAppPropertiesDoc";
    private final String id = STATIC_ID;

    private String APP_ID = "";
    private String PERMISSIONS = "";
    private String REDIRECT_URI = "";
    private String response_type = "code";
    private String APP_SECRET = "";

    public String getId() {
        return id;
    }

    public String getAPP_ID() {
        return APP_ID;
    }

    public void setAPP_ID(String APP_ID) {
        this.APP_ID = APP_ID;
    }

    public String getPERMISSIONS() {
        return PERMISSIONS;
    }

    public void setPERMISSIONS(String PERMISSIONS) {
        this.PERMISSIONS = PERMISSIONS;
    }

    public String getREDIRECT_URI() {
        return REDIRECT_URI;
    }

    public void setREDIRECT_URI(String REDIRECT_URI) {
        this.REDIRECT_URI = REDIRECT_URI;
    }

    public String getResponse_type() {
        return response_type;
    }

    public void setResponse_type(String response_type) {
        this.response_type = response_type;
    }

    public String getAPP_SECRET() {
        return APP_SECRET;
    }

    public void setAPP_SECRET(String APP_SECRET) {
        this.APP_SECRET = APP_SECRET;
    }
}
