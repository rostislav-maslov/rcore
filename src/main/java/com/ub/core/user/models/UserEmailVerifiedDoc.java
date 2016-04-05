package com.ub.core.user.models;

import org.bson.types.ObjectId;

import java.util.HashMap;
import java.util.Map;

public class UserEmailVerifiedDoc extends UserDoc {
    private String code;
    private Integer numberOfRetries = 0;
    private Boolean isVerified = false;
    private Map<String, ObjectId> customId = new HashMap<String, ObjectId>();

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getNumberOfRetries() {
        return numberOfRetries;
    }

    public void setNumberOfRetries(Integer numberOfRetries) {
        this.numberOfRetries = numberOfRetries;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(Boolean isVerified) {
        this.isVerified = isVerified;
    }

    public Map<String, ObjectId> getCustomId() {
        return customId;
    }

    public void setCustomId(Map<String, ObjectId> customId) {
        this.customId = customId;
    }
}
