package com.ub.core.seo.seoTags.models;

public class SeoTags {
    private String metaTitle;
    private String metaDescription;
    private String metaKeywords;
    private String title;
    private String h1;

    public SeoTags() {
    }

    public SeoTags(String title) {
        this.title = title;
    }

    public SeoTags(String metaTitle, String title) {
        this.metaTitle = metaTitle;
        this.title = title;
    }

    public SeoTags(String metaTitle, String title, String h1) {
        this.metaTitle = metaTitle;
        this.title = title;
        this.h1 = h1;
    }

    public SeoTags(String metaTitle, String metaDescription, String metaKeywords, String title, String h1) {
        this.metaTitle = metaTitle;
        this.metaDescription = metaDescription;
        this.metaKeywords = metaKeywords;
        this.title = title;
        this.h1 = h1;
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = metaTitle;
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = metaDescription;
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = metaKeywords;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getH1() {
        return h1;
    }

    public void setH1(String h1) {
        this.h1 = h1;
    }
}
