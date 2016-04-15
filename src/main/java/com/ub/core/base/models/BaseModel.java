package com.ub.core.base.models;

import java.util.Date;

/**
 * Created by maslov on 15.02.16.
 */
public class BaseModel {
    protected Date createdAt = new Date();
    protected Date updateAt  = new Date();
    protected String timeZone = "GMT";

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
