package com.ub.core.user.models;

/**
 * Created by fyzu on 16.09.16.
 */
public enum UserLoginStatusEnum {
    SUCCESS("Успешный вход"), BLOCKED("Заблокирован"), PASSWORD_ERROR("Неверный пароль");

    UserLoginStatusEnum(String title) {
        this.title = title;
    }

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
