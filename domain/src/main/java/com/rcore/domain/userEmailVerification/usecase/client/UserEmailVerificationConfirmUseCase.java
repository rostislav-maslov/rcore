package com.rcore.domain.userEmailVerification.usecase.client;

import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.UserAlreadyExistException;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.userEmailVerification.entity.UserEmailVerificationEntity;
import com.rcore.domain.userEmailVerification.exception.UserEmailVerificationNotFoundException;
import com.rcore.domain.userEmailVerification.port.*;

import java.util.List;
import java.util.Optional;

public class UserEmailVerificationConfirmUseCase extends UserEmailVerificationBaseUseCase {

    private final UserCreateFromConfirmUseCase userCreateFromConfirmUseCase;

    public UserEmailVerificationConfirmUseCase(UserEmailVerificationRepository userEmailVerificationRepository, UserRepository userRepository, UserEmailVerificationIdGenerator userEmailVerificationIdGenerator, UserEmailVerificationCodeGenerator userEmailVerificationCodeGenerator, UserEmailVerificationSender userEmailVerificationSender, UserEmailVerificationLifetime userEmailVerificationLifetime, UserCreateFromConfirmUseCase userCreateFromConfirmUseCase) {
        super(userEmailVerificationRepository, userRepository);
        this.userCreateFromConfirmUseCase = userCreateFromConfirmUseCase;
    }


    public Optional<UserEntity> confirm(String email, String code, String password) throws UserEmailVerificationNotFoundException, UserAlreadyExistException {
        UserEntity userEntity = userRepository.findByEmail(email.toLowerCase()).orElseThrow(() -> new UserAlreadyExistException());

        List<UserEmailVerificationEntity> userEmailVerificationEntity = userEmailVerificationRepository.findNotVerifiedAndActive(email, code);
        if( userEmailVerificationEntity == null || userEmailVerificationEntity.size() == 0) {
            throw new UserEmailVerificationNotFoundException();
        }

        return userCreateFromConfirmUseCase.create(email, password);
    }

}
