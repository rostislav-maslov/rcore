package com.ub.core.picture.models;

import org.bson.types.ObjectId;

public class PictureSize {
    public static final String SEP = "x";

    private Integer width = 0;
    private Integer hieght = 0;
    private ObjectId fileId;

    public String getStringSize(){
        return width + SEP + hieght;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PictureSize that = (PictureSize) o;

        if (!hieght.equals(that.hieght)) return false;
        if (!width.equals(that.width)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = width.hashCode();
        result = 31 * result + hieght.hashCode();
        return result;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHieght() {
        return hieght;
    }

    public void setHieght(Integer hieght) {
        this.hieght = hieght;
    }

    public ObjectId getFileId() {
        return fileId;
    }

    public void setFileId(ObjectId fileId) {
        this.fileId = fileId;
    }
}
