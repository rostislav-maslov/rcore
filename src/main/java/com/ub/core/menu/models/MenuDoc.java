package com.ub.core.menu.models;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document
public class MenuDoc {

    @Id
    protected ObjectId id;
    protected String name;
    protected String url;
    @DBRef
    protected MenuDoc parent;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public MenuDoc getParent() {
        return parent;
    }

    public void setParent(MenuDoc parent) {
        this.parent = parent;
    }
}
