package com.ub.core.pages.views;

import java.util.Date;
import java.util.List;

public class PageView {
    protected String id;
    protected String title;
    protected List<String> tags;
    protected String tagsLine;
    protected String status;
    protected Date startPublishing;
    protected Date finishPublishing;
    protected Date createdDate;
    protected String createdById;
    protected String createdByName;
    protected String createdByAlias;
    protected Date modifiedDate;
    protected String modifiedById;
    protected String modifiedByName;
    protected String metaDescription;
    protected String metaKeywords;
    protected String author;
    protected String contentRights;
    protected String content;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTagsLine() {
        return tagsLine;
    }

    public void setTagsLine(String tagsLine) {
        this.tagsLine = tagsLine;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStartPublishing() {
        return startPublishing;
    }

    public void setStartPublishing(Date startPublishing) {
        this.startPublishing = startPublishing;
    }

    public Date getFinishPublishing() {
        return finishPublishing;
    }

    public void setFinishPublishing(Date finishPublishing) {
        this.finishPublishing = finishPublishing;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedById() {
        return createdById;
    }

    public void setCreatedById(String createdById) {
        this.createdById = createdById;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getCreatedByAlias() {
        return createdByAlias;
    }

    public void setCreatedByAlias(String createdByAlias) {
        this.createdByAlias = createdByAlias;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getModifiedById() {
        return modifiedById;
    }

    public void setModifiedById(String modifiedById) {
        this.modifiedById = modifiedById;
    }

    public String getModifiedByName() {
        return modifiedByName;
    }

    public void setModifiedByName(String modifiedByName) {
        this.modifiedByName = modifiedByName;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContentRights() {
        return contentRights;
    }

    public void setContentRights(String contentRights) {
        this.contentRights = contentRights;
    }
}
