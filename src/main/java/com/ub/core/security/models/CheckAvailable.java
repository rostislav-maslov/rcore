package com.ub.core.security.models;

import com.ub.core.base.role.Role;

public class CheckAvailable {
    private Boolean logged;
    private String goAfterFailLogin = "";
    private Boolean needRole;
    private Role role;

    public Boolean getLogged() {
        return logged;
    }

    public void setLogged(Boolean logged) {
        this.logged = logged;
    }

    public Boolean getNeedRole() {
        return needRole;
    }

    public void setNeedRole(Boolean needRole) {
        this.needRole = needRole;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getGoAfterFailLogin() {
        return goAfterFailLogin;
    }

    public void setGoAfterFailLogin(String goAfterFailLogin) {
        this.goAfterFailLogin = goAfterFailLogin;
    }
}
