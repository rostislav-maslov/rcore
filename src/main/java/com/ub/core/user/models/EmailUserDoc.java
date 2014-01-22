package com.ub.core.user.models;

import org.hibernate.validator.constraints.Email;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Document
public class EmailUserDoc implements Serializable{
    @Id
    @Email
    @Field(value = "_id")
    protected String id;

    protected   UserDocStatuses userDocStatuses;

    protected String vericationCode;

    @DBRef
    @NotNull
    protected UserDoc userDoc;

    @NotNull
    protected String password;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmailUserDoc that = (EmailUserDoc) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    public String getId(){
        return id;
    }

    public void setId(String id){
        this.id = id;
    }

    public String getEmail() {
        return id;
    }

    public void setEmail(String email) {
        this.id = email;
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
