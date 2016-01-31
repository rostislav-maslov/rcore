package com.ub.core.seo.seoTags.models;

import org.springframework.web.util.HtmlUtils;

public class SeoTags {
    private String metaTitle;
    private String metaDescription;
    private String metaKeywords;
    private String title;
    private String h1;
    private String relCanonical;

    public SeoTags() {
    }

    public SeoTags(String title) {
        this.title = HtmlUtils.htmlEscape(title);
    }

    public SeoTags(String metaTitle, String title) {
        this.metaTitle = HtmlUtils.htmlEscape(metaTitle);
        this.title = HtmlUtils.htmlEscape(title);
    }

    public SeoTags(String metaTitle, String title, String h1) {
        this.metaTitle = HtmlUtils.htmlEscape(metaTitle);
        this.title = HtmlUtils.htmlEscape(title);
        this.h1 = HtmlUtils.htmlEscape(h1);
    }

    public SeoTags(String metaTitle, String metaDescription, String metaKeywords, String title, String h1) {
        this.metaTitle = HtmlUtils.htmlEscape(metaTitle);
        this.metaDescription = HtmlUtils.htmlEscape(metaDescription);
        this.metaKeywords = HtmlUtils.htmlEscape(metaKeywords);
        this.title = HtmlUtils.htmlEscape(title);
        this.h1 = HtmlUtils.htmlEscape(h1);
    }

    public String getMetaTitle() {
        return metaTitle;
    }

    public void setMetaTitle(String metaTitle) {
        this.metaTitle = HtmlUtils.htmlEscape(metaTitle);
    }

    public String getMetaDescription() {
        return metaDescription;
    }

    public void setMetaDescription(String metaDescription) {
        this.metaDescription = HtmlUtils.htmlEscape(metaDescription);
    }

    public String getMetaKeywords() {
        return metaKeywords;
    }

    public void setMetaKeywords(String metaKeywords) {
        this.metaKeywords = HtmlUtils.htmlEscape(metaKeywords);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = HtmlUtils.htmlEscape(title);
    }

    public String getH1() {
        return h1;
    }

    public void setH1(String h1) {
        this.h1 = HtmlUtils.htmlEscape(h1);
    }

    public String getRelCanonical() {
        return relCanonical;
    }

    public void setRelCanonical(String relCanonical) {
        this.relCanonical = HtmlUtils.htmlEscape(relCanonical);
    }
}
