package com.ub.twitter.models;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TwitterAppPropertiesDoc {
    public static final String STATIC_ID = "TwitterAppPropertiesDoc";

    private final String id = STATIC_ID;

    private String yourApiKey = "";
    private String yourApiSecret = "";

    public String getId() {
        return id;
    }

    public String getYourApiKey() {
        return yourApiKey;
    }

    public void setYourApiKey(String yourApiKey) {
        this.yourApiKey = yourApiKey;
    }

    public String getYourApiSecret() {
        return yourApiSecret;
    }

    public void setYourApiSecret(String yourApiSecret) {
        this.yourApiSecret = yourApiSecret;
    }
}
