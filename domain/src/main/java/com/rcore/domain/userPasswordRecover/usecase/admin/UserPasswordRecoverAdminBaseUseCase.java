package com.rcore.domain.userPasswordRecover.usecase.admin;

import com.rcore.domain.access.entity.Access;
import com.rcore.domain.base.usecase.AdminUseCase;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.userPasswordRecover.port.UserPasswordRecoverRepository;

class UserPasswordRecoverAdminBaseUseCase extends AdminUseCase {

    protected final UserPasswordRecoverRepository userPasswordRecoverRepository;

    public UserPasswordRecoverAdminBaseUseCase(UserPasswordRecoverRepository userPasswordRecoverRepository, Access accessAccess, AuthorizationByTokenUseCase authorizationByTokenUseCase) {
        super(accessAccess, authorizationByTokenUseCase);
        this.userPasswordRecoverRepository = userPasswordRecoverRepository;
    }

}
