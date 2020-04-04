package com.rcore.domain.base.usecase;

import com.rcore.domain.role.entity.AdminAccessRole;
import com.rcore.domain.role.entity.Role;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;

import java.util.Set;

public abstract class AdminUseCase extends AuthorizationUseCase {

    public AdminUseCase(UserEntity actor, Set<Role> availableRolesForCase) throws AuthorizationException {
        super(Role.concat(availableRolesForCase, new AdminAccessRole()), true, actor);
    }

    public AdminUseCase(UserEntity actor, Role availableRoleForCase) throws AuthorizationException {
        super(Role.concat(availableRoleForCase, new AdminAccessRole()), true, actor);
    }

}
