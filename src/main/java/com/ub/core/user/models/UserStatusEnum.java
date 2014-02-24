package com.ub.core.user.models;

public enum UserStatusEnum {
    ACTIVE, BLOCK;

    public String getTitle(){
        if(equals(ACTIVE)) return "активный";
        if(equals(BLOCK)) return "заблокирован";
        return "";
    }
}
