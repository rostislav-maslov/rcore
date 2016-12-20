package com.ub.core.pictureTask.models;

import com.ub.core.picture.models.PictureSizeType;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import com.ub.core.base.models.BaseModel;


import javax.persistence.Id;

@Document
public class PictureTaskDoc extends BaseModel {
    @Id
    private ObjectId id;
    private ObjectId pictureId;
	private String exc;
	private PictureTaskStatus status;
	private Integer width;
	private PictureSizeType pictureSizeType = PictureSizeType.IMAGE_MAGIC;
	
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
    
    public ObjectId getPictureId() {
        return pictureId;
    }

    public void setPictureId(ObjectId pictureId) {
        this.pictureId = pictureId;
    }

    public String getExc() {
        return exc;
    }

    public void setExc(String exc) {
        this.exc = exc;
    }

    public PictureTaskStatus getStatus() {
        return status;
    }

    public void setStatus(PictureTaskStatus status) {
        this.status = status;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public PictureSizeType getPictureSizeType() {
        return pictureSizeType;
    }

    public void setPictureSizeType(PictureSizeType pictureSizeType) {
        this.pictureSizeType = pictureSizeType;
    }
}
