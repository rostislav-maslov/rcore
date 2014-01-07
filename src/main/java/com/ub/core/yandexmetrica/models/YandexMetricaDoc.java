package com.ub.core.yandexmetrica.models;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document
public class YandexMetricaDoc {
    @Id
    protected Long id;
    protected String content;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
