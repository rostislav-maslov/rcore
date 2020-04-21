package com.rcore.domain.userEmailVerification.usecase.admin;

import com.rcore.domain.access.entity.Access;
import com.rcore.domain.base.usecase.AdminUseCase;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.userEmailVerification.port.UserEmailVerificationRepository;

class UserEmailVerificationAdminBaseUseCase extends AdminUseCase {

    protected final UserEmailVerificationRepository userEmailVerificationRepository;

    public UserEmailVerificationAdminBaseUseCase(UserEntity actor, UserEmailVerificationRepository userEmailVerificationRepository, Access accessAccess) throws AuthorizationException {
        super(actor, accessAccess);
        this.userEmailVerificationRepository = userEmailVerificationRepository;
    }

}
