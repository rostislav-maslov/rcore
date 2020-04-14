package com.rcore.domain.userEmailVerification.usecase.admin;

import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.userEmailVerification.entity.UserEmailVerificationEntity;
import com.rcore.domain.userEmailVerification.port.UserEmailVerificationRepository;
import com.rcore.domain.userEmailVerification.role.AdminUserEmailVerificationUpdateRole;

import java.time.LocalDateTime;

public class UserEmailVerificationUpdateUseCase  extends UserEmailVerificationAdminBaseUseCase {

    public UserEmailVerificationUpdateUseCase(UserEntity actor, UserEmailVerificationRepository userEmailVerificationRepository)throws AuthorizationException {
        super(actor, userEmailVerificationRepository, new AdminUserEmailVerificationUpdateRole());
    }

    public UserEmailVerificationEntity update(UserEmailVerificationEntity userEmailVerificationEntity){
        userEmailVerificationEntity.setUpdatedAt(LocalDateTime.now());
        return userEmailVerificationRepository.save(userEmailVerificationEntity);
    }


}