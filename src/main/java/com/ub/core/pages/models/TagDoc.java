package com.ub.core.pages.models;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "ub_pages_module_tag")
public class TagDoc {
    @Id
    protected String id;
}
