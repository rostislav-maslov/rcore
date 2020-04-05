package com.rcore.domain.userEmailVerification.usecase.admin;

import com.rcore.domain.userEmailVerification.entity.UserEmailVerificationEntity;
import com.rcore.domain.userEmailVerification.port.UserEmailVerificationRepository;
import com.rcore.domain.userEmailVerification.role.AdminUserEmailVerificationUpdateRole;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;

public class UserEmailVerificationUpdateUseCase  extends UserEmailVerificationAdminBaseUseCase {

    public UserEmailVerificationUpdateUseCase(UserEntity actor, UserEmailVerificationRepository userEmailVerificationRepository)throws AuthorizationException {
        super(actor, userEmailVerificationRepository, new AdminUserEmailVerificationUpdateRole());
    }

    public UserEmailVerificationEntity update(UserEmailVerificationEntity userEmailVerificationEntity){
        userEmailVerificationEntity.setUpdatedAt(new Date());
        return userEmailVerificationRepository.save(userEmailVerificationEntity);
    }


}