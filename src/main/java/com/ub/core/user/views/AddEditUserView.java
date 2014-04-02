package com.ub.core.user.views;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.Size;

public class AddEditUserView {


    @Email(message = "неверно задан email")
    private String email;
    @Size(min = 6, message = "пароль должен быть более 6 символов")
    private String password;

    //@NotNull(message = "укажите роль")
    private String role;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
