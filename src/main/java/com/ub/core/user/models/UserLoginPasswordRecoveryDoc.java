package com.ub.core.user.models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserLoginPasswordRecoveryDoc {
    private ObjectId id;
    private Integer counter = 0;
    private Boolean isRecovered = false;
    private ObjectId userId;
    private String code;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public ObjectId getUserId() {
        return userId;
    }

    public void setUserId(ObjectId userId) {
        this.userId = userId;
    }

    public Boolean getIsRecovered() {
        return isRecovered;
    }

    public void setIsRecovered(Boolean isRecovered) {
        this.isRecovered = isRecovered;
    }

    public Integer getCounter() {
        return counter;
    }

    public void setCounter(Integer counter) {
        this.counter = counter;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
}
