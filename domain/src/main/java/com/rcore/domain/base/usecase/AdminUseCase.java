package com.rcore.domain.base.usecase;

import com.rcore.domain.access.entity.Access;
import com.rcore.domain.access.entity.AdminAccess;
import com.rcore.domain.access.utils.AccessUtils;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;

import java.util.Set;

public abstract class AdminUseCase extends AuthorizationUseCase {

    public AdminUseCase(Set<Access> availableRolesForCase, AuthorizationByTokenUseCase authorizationByTokenUseCase) {
        super(AccessUtils.concat(availableRolesForCase, new AdminAccess()), true, authorizationByTokenUseCase);
    }

    public AdminUseCase(Access availableAccessForCase, AuthorizationByTokenUseCase authorizationByTokenUseCase) {
        super(AccessUtils.concat(availableAccessForCase, new AdminAccess()), true, authorizationByTokenUseCase);
    }

}
