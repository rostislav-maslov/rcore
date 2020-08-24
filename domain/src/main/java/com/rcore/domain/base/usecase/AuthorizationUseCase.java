package com.rcore.domain.base.usecase;

import com.rcore.domain.access.entity.GodModAccess;
import com.rcore.domain.access.entity.Access;
import com.rcore.domain.access.utils.AccessUtils;
import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public abstract class AuthorizationUseCase {

    protected UserEntity actor;
    protected final Set<Access> availableAccesses;
    protected final Boolean onlyAuthorized;
    protected final AuthorizationByTokenUseCase authorizationByTokenUseCase;

    protected UserEntity checkAccess() throws AuthorizationException, AuthenticationException {
        this.actor = authorizationByTokenUseCase.currentUser();

        if (canExecute() == false) {
            throw new AuthorizationException();
        }

        return actor;
    }

    public Boolean canExecute() {
        if (onlyAuthorized == false) return true;

        if (actor == null) return false;

        if (actor.hasAccess(new GodModAccess())) return true;

        for (Access access : availableAccesses) {
            if (AccessUtils.hasAccess(actor.getAccesses(), access)) return true;
        }

        return false;
    }


}
