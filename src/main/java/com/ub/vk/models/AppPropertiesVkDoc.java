package com.ub.vk.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AppPropertiesVkDoc {
    public static final String STATIC_ID = "VkAppPropertiesDoc";
    private final String id = STATIC_ID;

    private String APP_ID = "";
    private String PERMISSIONS = "";
    private String REDIRECT_URI = "";
    private String response_type = "code";
    private String API_VERSION = "";
    private String APP_SECRET = "";


    public String getAPP_ID() {
        return APP_ID;
    }

    public void setAPP_ID(String APP_ID) {
        this.APP_ID = APP_ID;
    }

    public String getAPP_SECRET() {
        return APP_SECRET;
    }

    public void setAPP_SECRET(String APP_SECRET) {
        this.APP_SECRET = APP_SECRET;
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

    public String getAPI_VERSION() {
        return API_VERSION;
    }

    public void setAPI_VERSION(String API_VERSION) {
        this.API_VERSION = API_VERSION;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
//        this.id = id;
    }
}
