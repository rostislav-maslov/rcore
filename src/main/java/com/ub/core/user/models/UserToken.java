package com.ub.core.user.models;

import java.util.Date;

/**
 * Created by maslov on 12.05.16.
 */
public class UserToken {
    private String token;
    private Integer expire = 3600;
    private Date createAt = new Date();

    public Boolean isActive(){
        return true;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getExpire() {
        return expire;
    }

    public void setExpire(Integer expire) {
        this.expire = expire;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }
}
