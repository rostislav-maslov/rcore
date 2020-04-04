package com.ub.linkedin.models;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Eduard on 02.10.2015.
 */
@Document
public class AppPropertiesLinkedinDoc {
    public static final String STATIC_ID = "LinkedinAppPropertiesDoc";

    private final String id = STATIC_ID;

    private String client_id = "";
    private String client_secret = "";
    private String redirect_uri = "";
    private String response_type = "code";
    private String scope = "";
    private String state = "";

    public String getId() {
        return id;
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

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
