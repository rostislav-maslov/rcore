package com.ub.core.user.models;

import org.bson.types.ObjectId;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Document
public class EmailUserDoc{
    @Id
    @Email
    @Field(value = "_id")
    protected String email;

    protected   UserDocStatuses userDocStatuses;

    protected String vericationCode;

    @DBRef
    @NotNull
    protected UserDoc userDoc;

    @NotNull
    protected String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UserDoc getUserDoc() {
        return userDoc;
    }

    public void setUserDoc(UserDoc userDoc) {
        this.userDoc = userDoc;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserDocStatuses getUserDocStatuses() {
        return userDocStatuses;
    }

    public void setUserDocStatuses(UserDocStatuses userDocStatuses) {
        this.userDocStatuses = userDocStatuses;
    }

    public String getVericationCode() {
        return vericationCode;
    }

    public void setVericationCode(String vericationCode) {
        this.vericationCode = vericationCode;
    }
}
