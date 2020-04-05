package com.rcore.domain.userEmailVerification.usecase.admin;

import com.rcore.domain.base.usecase.AdminUseCase;
import com.rcore.domain.role.entity.Role;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.userEmailVerification.port.UserEmailVerificationRepository;

class UserEmailVerificationAdminBaseUseCase extends AdminUseCase {

    protected final UserEmailVerificationRepository userEmailVerificationRepository;

    public UserEmailVerificationAdminBaseUseCase(UserEntity actor, UserEmailVerificationRepository userEmailVerificationRepository, Role accessRole) throws AuthorizationException {
        super(actor, accessRole);
        this.userEmailVerificationRepository = userEmailVerificationRepository;
    }

}
