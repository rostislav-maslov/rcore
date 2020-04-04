package com.ub.core.role.roles;

import com.ub.core.base.role.Role;
import com.ub.core.user.routes.UserLoginRoutes;

public class AvailableForRole extends Role {
    public AvailableForRole() {
        this.id = this.getClass().getName();
        this.roleTitle = "Управление ролями пользователей";
        this.roleDescription = "Управление ролями пользователей";
        this.goAfterFail = UserLoginRoutes.ACCESS_DENIED;
    }
}
