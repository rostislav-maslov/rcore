package com.ub.google.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AppPropertiesGoogleDoc {
    public static final String STATIC_ID = "GoogleAppPropertiesDoc";

    private final String id = STATIC_ID;

    private String app_key = "";
    private String client_id = "";
    private String client_secret = "";
    private String application_name = "";
    private String redirect_uri = "";
    private String response_type = "code";

    public String getId() {
        return id;
    }

    public String getApp_key() {
        return app_key;
    }

    public void setApp_key(String app_key) {
        this.app_key = app_key;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClient_secret() {
        return client_secret;
    }

    public void setClient_secret(String client_secret) {
        this.client_secret = client_secret;
    }

    public String getApplication_name() {
        return application_name;
    }

    public void setApplication_name(String application_name) {
        this.application_name = application_name;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getResponse_type() {
        return response_type;
    }

    public void setResponse_type(String response_type) {
        this.response_type = response_type;
    }
}
