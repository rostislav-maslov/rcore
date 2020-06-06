package com.rcore.domain.userEmailVerification.usecase.admin;

import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.userEmailVerification.entity.UserEmailVerificationEntity;
import com.rcore.domain.userEmailVerification.port.UserEmailVerificationRepository;
import com.rcore.domain.userEmailVerification.access.AdminUserEmailVerificationDeleteAccess;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;

public class UserEmailVerificationDeleteUseCase extends UserEmailVerificationAdminBaseUseCase {

    public UserEmailVerificationDeleteUseCase(UserEmailVerificationRepository userEmailVerificationRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase) throws AuthorizationException {
        super(userEmailVerificationRepository, new AdminUserEmailVerificationDeleteAccess(), authorizationByTokenUseCase);
    }

    public Boolean delete(UserEmailVerificationEntity userEmailVerificationEntity) throws AuthenticationException, AuthorizationException {
        checkAccess();

        userEmailVerificationRepository.delete(userEmailVerificationEntity);

        return true;
    }


}