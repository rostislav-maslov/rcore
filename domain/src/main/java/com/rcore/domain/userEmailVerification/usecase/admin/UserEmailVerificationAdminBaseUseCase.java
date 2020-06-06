package com.rcore.domain.userEmailVerification.usecase.admin;

import com.rcore.domain.access.entity.Access;
import com.rcore.domain.base.usecase.AdminUseCase;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.userEmailVerification.port.UserEmailVerificationRepository;

class UserEmailVerificationAdminBaseUseCase extends AdminUseCase {

    protected final UserEmailVerificationRepository userEmailVerificationRepository;

    public UserEmailVerificationAdminBaseUseCase(UserEmailVerificationRepository userEmailVerificationRepository, Access accessAccess, AuthorizationByTokenUseCase authorizationByTokenUseCase) throws AuthorizationException {
        super(accessAccess, authorizationByTokenUseCase);
        this.userEmailVerificationRepository = userEmailVerificationRepository;
    }

}
