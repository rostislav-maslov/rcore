package com.ub.core.user.roles;

import com.ub.core.base.role.Role;
import com.ub.core.user.routes.UserLoginRoutes;

public class AvailableChangePassword extends Role {
    public AvailableChangePassword() {
        this.id =  this.getClass().getName();
        this.roleTitle = "Редактирование паролей пользователей";
        this.roleDescription = "Редактирование паролей пользователей";
        this.goAfterFail = UserLoginRoutes.ACCESS_DENIED;
    }
}
