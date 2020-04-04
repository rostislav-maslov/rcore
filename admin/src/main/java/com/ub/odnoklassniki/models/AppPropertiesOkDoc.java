package com.ub.odnoklassniki.models;

public class AppPropertiesOkDoc {
    public static final String STATIC_ID = "OkAppPropertiesDoc";
    private final String id = STATIC_ID;

    private String CLIENT_ID = "";
    private String PUBLIC_KEY = "";
    private String SECRET_KEY = "";
    private String REDIRECT_URI = "";
    private String RESPONSE_TYPE = "code";

    public String getId() {
        return id;
    }

    public String getCLIENT_ID() {
        return CLIENT_ID;
    }

    public void setCLIENT_ID(String CLIENT_ID) {
        this.CLIENT_ID = CLIENT_ID;
    }

    public String getPUBLIC_KEY() {
        return PUBLIC_KEY;
    }

    public void setPUBLIC_KEY(String PUBLIC_KEY) {
        this.PUBLIC_KEY = PUBLIC_KEY;
    }

    public String getSECRET_KEY() {
        return SECRET_KEY;
    }

    public void setSECRET_KEY(String SECRET_KEY) {
        this.SECRET_KEY = SECRET_KEY;
    }

    public String getREDIRECT_URI() {
        return REDIRECT_URI;
    }

    public void setREDIRECT_URI(String REDIRECT_URI) {
        this.REDIRECT_URI = REDIRECT_URI;
    }

    public String getRESPONSE_TYPE() {
        return RESPONSE_TYPE;
    }

    public void setRESPONSE_TYPE(String RESPONSE_TYPE) {
        this.RESPONSE_TYPE = RESPONSE_TYPE;
    }
}
