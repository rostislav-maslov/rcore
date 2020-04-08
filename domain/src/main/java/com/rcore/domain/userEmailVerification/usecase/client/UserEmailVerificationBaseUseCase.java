package com.rcore.domain.userEmailVerification.usecase.client;

import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.userEmailVerification.entity.UserEmailVerificationEntity;
import com.rcore.domain.userEmailVerification.port.UserEmailVerificationRepository;

class UserEmailVerificationBaseUseCase{

    protected final UserEmailVerificationRepository userEmailVerificationRepository;
    protected final UserRepository userRepository;

    public UserEmailVerificationBaseUseCase(UserEmailVerificationRepository userEmailVerificationRepository, UserRepository userRepository) {
        this.userEmailVerificationRepository = userEmailVerificationRepository;
        this.userRepository = userRepository;
    }

}
