package com.rcore.domain.userEmailVerification.usecase.admin;

import com.rcore.domain.userEmailVerification.entity.UserEmailVerificationEntity;
import com.rcore.domain.userEmailVerification.port.UserEmailVerificationIdGenerator;
import com.rcore.domain.userEmailVerification.port.UserEmailVerificationRepository;
import com.rcore.domain.userEmailVerification.role.AdminUserEmailVerificationCreateRole;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;

import java.util.Optional;


public class UserEmailVerificationCreateUseCase extends UserEmailVerificationAdminBaseUseCase {
    private final UserEmailVerificationIdGenerator idGenerator;

    public UserEmailVerificationCreateUseCase(UserEntity actor, UserEmailVerificationRepository userEmailVerificationRepository, UserEmailVerificationIdGenerator idGenerator) throws AuthorizationException {
        super(actor, userEmailVerificationRepository, new AdminUserEmailVerificationCreateRole());
        this.idGenerator = idGenerator;
    }

    public UserEmailVerificationEntity create(UserEmailVerificationEntity userEmailVerificationEntity) {
        userEmailVerificationEntity.setId(idGenerator.generate());

        return userEmailVerificationRepository.save(userEmailVerificationEntity);
    }


}