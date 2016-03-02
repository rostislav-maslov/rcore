package com.ub.core.base.role;

import com.ub.core.role.models.RoleDoc;
import com.ub.core.user.routes.UserLoginRoutes;

public class Role {

    protected String id = this.getClass().getName();
    protected String roleTitle = "";
    protected String roleDescription = "";
    protected String goAfterFail = UserLoginRoutes.LOGIN;

    public Role() {
    }

    public Role(RoleDoc roleDoc) {
        this.id = roleDoc.getId();
        this.roleTitle = roleDoc.getRoleTitle();
        this.roleDescription = roleDoc.getRoleDescription();
        this.goAfterFail = roleDoc.getGoAfterFail();
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Role)) return false;

        Role that = (Role) o;

        return this.id.equals(that.getId());
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
