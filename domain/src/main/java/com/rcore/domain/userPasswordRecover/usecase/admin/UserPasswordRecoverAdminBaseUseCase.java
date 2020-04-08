package com.rcore.domain.userPasswordRecover.usecase.admin;

import com.rcore.domain.base.usecase.AdminUseCase;
import com.rcore.domain.role.entity.Role;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.userPasswordRecover.port.UserPasswordRecoverRepository;

class UserPasswordRecoverAdminBaseUseCase extends AdminUseCase {

    protected final UserPasswordRecoverRepository userPasswordRecoverRepository;

    public UserPasswordRecoverAdminBaseUseCase(UserEntity actor, UserPasswordRecoverRepository userPasswordRecoverRepository, Role accessRole) throws AuthorizationException {
        super(actor, accessRole);
        this.userPasswordRecoverRepository = userPasswordRecoverRepository;
    }

}
