package com.rcore.domain.base.usecase;

import com.rcore.domain.role.entity.GodModRole;
import com.rcore.domain.role.entity.Role;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;

import java.util.Set;

public abstract class AuthorizationUseCase {

    protected final UserEntity actor;
    protected final Set<Role> availableRoles;
    protected final Boolean onlyAuthorized;

    protected AuthorizationUseCase(Set<Role> availableRoles, Boolean onlyAuthorized, UserEntity actor) throws AuthorizationException {
        this.availableRoles = availableRoles;
        this.onlyAuthorized = onlyAuthorized;
        this.actor = actor;

        if( canExecute() == false ) {
            throw new AuthorizationException();
        }
    }

    public Boolean canExecute() {
        if(onlyAuthorized == false) return true;

        if(actor == null) return false;

        if(actor.hasRole(new GodModRole())) return true;

        for(Role role: availableRoles){
            if(Role.hasAccess(actor.getRoles(), role) == false) return false;
        }

        return true;
    }


}
