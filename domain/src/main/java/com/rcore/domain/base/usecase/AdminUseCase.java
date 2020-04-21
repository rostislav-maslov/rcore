package com.rcore.domain.base.usecase;

import com.rcore.domain.access.entity.Access;
import com.rcore.domain.access.entity.AdminAccess;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;

import java.util.Set;

public abstract class AdminUseCase extends AuthorizationUseCase {

    public AdminUseCase(UserEntity actor, Set<Access> availableRolesForCase) throws AuthorizationException {
        super(Access.concat(availableRolesForCase, new AdminAccess()), true, actor);
    }

    public AdminUseCase(UserEntity actor, Access availableAccessForCase) throws AuthorizationException {
        super(Access.concat(availableAccessForCase, new AdminAccess()), true, actor);
    }

}
