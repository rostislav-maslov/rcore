package com.ub.core.user.models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Document
public class EmailUserDoc{
    @Id
    protected ObjectId id;
    @Indexed(unique = true)
    protected String email;

    @DBRef
    @NotNull
    protected UserDoc userDoc;

    @NotNull
    protected String password;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

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
}
