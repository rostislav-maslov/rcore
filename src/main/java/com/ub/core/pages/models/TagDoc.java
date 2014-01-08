package com.ub.core.pages.models;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "ub_pages_module_tag")
public class TagDoc {
    @Id
    protected String id;

    public TagDoc() {
    }

    public TagDoc(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
