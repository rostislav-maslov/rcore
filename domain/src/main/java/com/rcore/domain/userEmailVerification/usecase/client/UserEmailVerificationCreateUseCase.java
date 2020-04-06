package com.rcore.domain.userEmailVerification.usecase.client;

import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.UserAlreadyExistException;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.userEmailVerification.entity.UserEmailVerificationEntity;
import com.rcore.domain.userEmailVerification.port.*;

import java.util.Date;
import java.util.Optional;

public class UserEmailVerificationCreateUseCase extends UserEmailVerificationBaseUseCase {

    private final UserEmailVerificationIdGenerator userEmailVerificationIdGenerator;
    private final UserEmailVerificationCodeGenerator userEmailVerificationCodeGenerator;
    private final UserEmailVerificationSender userEmailVerificationSender;
    private final UserEmailVerificationLifetime userEmailVerificationLifetime;

    public UserEmailVerificationCreateUseCase(UserEmailVerificationRepository userEmailVerificationRepository, UserRepository userRepository, UserEmailVerificationIdGenerator userEmailVerificationIdGenerator, UserEmailVerificationCodeGenerator userEmailVerificationCodeGenerator, UserEmailVerificationSender userEmailVerificationSender, UserEmailVerificationLifetime userEmailVerificationLifetime) {
        super(userEmailVerificationRepository, userRepository);
        this.userEmailVerificationIdGenerator = userEmailVerificationIdGenerator;
        this.userEmailVerificationCodeGenerator = userEmailVerificationCodeGenerator;
        this.userEmailVerificationSender = userEmailVerificationSender;
        this.userEmailVerificationLifetime = userEmailVerificationLifetime;
    }


    public Optional<UserEmailVerificationEntity> create(String email) throws UserAlreadyExistException {
        userRepository.findByEmail(email.toLowerCase())
                .orElseThrow(() -> new UserAlreadyExistException());

        UserEmailVerificationEntity userEmailVerificationEntity = new UserEmailVerificationEntity();

        userEmailVerificationEntity.setId(userEmailVerificationIdGenerator.generate());
        userEmailVerificationEntity.setCode(userEmailVerificationCodeGenerator.generate());
        userEmailVerificationEntity.setExpiredDate(new Date(
                new Date().getTime() + userEmailVerificationLifetime.emailLifetime()
        ));
        userEmailVerificationEntity.setVerified(false);

       return userEmailVerificationRepository.save(userEmailVerificationEntity)
                .map(userEmailVerification -> {
                    userEmailVerificationSender.sendEmail(userEmailVerification);
                    return userEmailVerification;
                });
    }

}
