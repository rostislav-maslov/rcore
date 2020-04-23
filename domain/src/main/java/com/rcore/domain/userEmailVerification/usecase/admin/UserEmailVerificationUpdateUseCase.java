package com.rcore.domain.userEmailVerification.usecase.admin;

import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.userEmailVerification.access.AdminUserEmailVerificationUpdateAccess;
import com.rcore.domain.userEmailVerification.entity.UserEmailVerificationEntity;
import com.rcore.domain.userEmailVerification.port.UserEmailVerificationRepository;

import java.time.LocalDateTime;

public class UserEmailVerificationUpdateUseCase extends UserEmailVerificationAdminBaseUseCase {

    public UserEmailVerificationUpdateUseCase(UserEmailVerificationRepository userEmailVerificationRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase) throws AuthorizationException {
        super(userEmailVerificationRepository, new AdminUserEmailVerificationUpdateAccess(), authorizationByTokenUseCase);
    }

    public UserEmailVerificationEntity update(UserEmailVerificationEntity userEmailVerificationEntity) throws AuthenticationException, AuthorizationException {
        checkAccess();

        userEmailVerificationEntity.setUpdatedAt(LocalDateTime.now());
        return userEmailVerificationRepository.save(userEmailVerificationEntity);
    }


}