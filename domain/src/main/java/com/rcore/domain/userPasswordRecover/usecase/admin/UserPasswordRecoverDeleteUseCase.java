package com.rcore.domain.userPasswordRecover.usecase.admin;

import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.userPasswordRecover.entity.UserPasswordRecoverEntity;
import com.rcore.domain.userPasswordRecover.port.UserPasswordRecoverRepository;
import com.rcore.domain.userPasswordRecover.access.AdminUserPasswordRecoverDeleteAccess;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;

public class UserPasswordRecoverDeleteUseCase  extends UserPasswordRecoverAdminBaseUseCase {

    public UserPasswordRecoverDeleteUseCase(UserPasswordRecoverRepository userPasswordRecoverRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase) {
        super(userPasswordRecoverRepository, new AdminUserPasswordRecoverDeleteAccess(), authorizationByTokenUseCase);
    }

    public Boolean delete(UserPasswordRecoverEntity userPasswordRecoverEntity) throws AuthenticationException, AuthorizationException {
        checkAccess();

        userPasswordRecoverRepository.delete(userPasswordRecoverEntity);

        return true;
    }


}