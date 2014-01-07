package com.ub.core.user.models;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Document
public class EmailUserDoc{

    @Id
    protected String email;

    @DBRef
    @NotNull
    protected UserDoc userDoc;

    @NotNull
    protected String password;
}
