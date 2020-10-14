package com.rcore.domain.userEmailVerification.usecase.admin;

import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.exception.TokenExpiredException;
import com.rcore.domain.userEmailVerification.entity.UserEmailVerificationEntity;
import com.rcore.domain.userEmailVerification.port.UserEmailVerificationIdGenerator;
import com.rcore.domain.userEmailVerification.port.UserEmailVerificationRepository;
import com.rcore.domain.userEmailVerification.access.AdminUserEmailVerificationCreateAccess;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;


public class UserEmailVerificationCreateUseCase extends UserEmailVerificationAdminBaseUseCase {
    private final UserEmailVerificationIdGenerator idGenerator;

    public UserEmailVerificationCreateUseCase(UserEmailVerificationRepository userEmailVerificationRepository, UserEmailVerificationIdGenerator idGenerator, AuthorizationByTokenUseCase authorizationByTokenUseCase) throws AuthorizationException {
        super(userEmailVerificationRepository, new AdminUserEmailVerificationCreateAccess(), authorizationByTokenUseCase);
        this.idGenerator = idGenerator;
    }

    public UserEmailVerificationEntity create(UserEmailVerificationEntity userEmailVerificationEntity) throws AuthenticationException, AuthorizationException, TokenExpiredException {
        checkAccess();

        userEmailVerificationEntity.setId(idGenerator.generate());

        userEmailVerificationEntity = userEmailVerificationRepository.save(userEmailVerificationEntity);
        return userEmailVerificationEntity;
    }


}