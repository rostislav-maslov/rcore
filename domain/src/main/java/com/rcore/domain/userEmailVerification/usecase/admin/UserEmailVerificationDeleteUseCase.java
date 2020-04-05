package com.rcore.domain.userEmailVerification.usecase.admin;

import com.rcore.domain.userEmailVerification.entity.UserEmailVerificationEntity;
import com.rcore.domain.userEmailVerification.port.UserEmailVerificationRepository;
import com.rcore.domain.userEmailVerification.role.AdminUserEmailVerificationDeleteRole;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;

public class UserEmailVerificationDeleteUseCase  extends UserEmailVerificationAdminBaseUseCase {

    public UserEmailVerificationDeleteUseCase(UserEntity actor, UserEmailVerificationRepository userEmailVerificationRepository) throws AuthorizationException {
        super(actor, userEmailVerificationRepository, new AdminUserEmailVerificationDeleteRole());
    }

    public Boolean delete(UserEmailVerificationEntity userEmailVerificationEntity){
        userEmailVerificationRepository.delete(userEmailVerificationEntity);

        return true;
    }


}