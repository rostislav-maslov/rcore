package com.ub.core.role.models;

import com.ub.core.base.role.Role;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class RoleDoc {
    @Id
    private String id;
    private String roleTitle;
    private String roleDescription;
    private String goAfterFail;

    public RoleDoc() {
    }

    public RoleDoc(Role role) {
        this.id = role.getId();
        this.roleTitle = role.getRoleTitle();
        this.roleDescription = role.getRoleDescription();
        this.goAfterFail = role.getGoAfterFail();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    public String getRoleDescription() {
        return roleDescription;
    }

    public void setRoleDescription(String roleDescription) {
        this.roleDescription = roleDescription;
    }

    public String getGoAfterFail() {
        return goAfterFail;
    }

    public void setGoAfterFail(String goAfterFail) {
        this.goAfterFail = goAfterFail;
    }
}
