package com.ub.core.pages.models;

import com.ub.core.pages.models.enums.PageStatus;
import com.ub.core.user.models.UserDoc;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Document(collection = "ub_pages_module_page")
public class PageDoc {

    @Id
    protected String url;

    @NotNull
    protected String title;

    @DBRef
    protected List<TagDoc> tags;

    @NotNull
    protected PageStatus status;

    protected Date startPublishing;

    protected Date finishPublishing;

    @NotNull
    protected Date createdDate;

    @NotNull
    @DBRef
    protected UserDoc createdBy;

    protected String createdByAlias;

    @NotNull
    protected Date modifiedDate;

    @NotNull
    @DBRef
    protected UserDoc modifiedBy;

    protected String metaDescription;

    protected String metaKeywords;

    protected String author;

    protected String contentRights;

    //Robots Use Global


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TagDoc> getTags() {
        return tags;
    }

    public void setTags(List<TagDoc> tags) {
        this.tags = tags;
    }

    public PageStatus getStatus() {
        return status;
    }

    public void setStatus(PageStatus status) {
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

    public UserDoc getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(UserDoc createdBy) {
        this.createdBy = createdBy;
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

    public UserDoc getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(UserDoc modifiedBy) {
        this.modifiedBy = modifiedBy;
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
