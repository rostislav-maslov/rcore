package com.rcore.domain.token.entity;

import java.util.Date;

public class TokenEntity {
    public enum Type{
        ACCESS, REFRESH
    }

    public enum CreateFrom{
        LOGIN, REFRESH;
    }

    public enum Status{
        ACTIVE, INACTIVE
    }

    private String token;
    private String userId;
    private Long expire = 2 * 24 * 60 * 60 * 1000l;
    private Date createAt = new Date();
    private Date expireAt = new Date();

    private Type type;
    private Status status = Status.ACTIVE;

    private String createFromToken;
    private CreateFrom createFromType;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpire() {
        return expire;
    }

    public void setExpire(Long expire) {
        this.expire = expire;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getExpireAt() {
        return expireAt;
    }

    public void setExpireAt(Date expireAt) {
        this.expireAt = expireAt;
    }
}
