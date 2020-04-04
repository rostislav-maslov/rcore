package com.ub.core.user.views;

public class UserListView {

    public final static String TITLE_EMAIL = "email";
    public final static String TITLE_ROLE = "роль";
    public final static String TITLE_CONFIGURATION = "действия";
    public final static String TITLE_STATUS = "статус";

    private String email;
    private String password;
    private String role;
    private String status;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
