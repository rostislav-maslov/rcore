package com.ub.core.user.views;

import javax.validation.constraints.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Anton
 * Date: 1/17/14
 * Time: 7:33 AM
 * To change this template use File | Settings | File Templates.
 */
public class AddEditRoleView {

//    @Pattern(regexp="/ROLE_*", message = "Значение должно быть вида ROLE_НАЗВАНИЕ")
    private String roleTitle;
    private String roleDescription;

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
}
