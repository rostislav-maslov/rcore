package com.rcore.domain.user.usecase.all;

import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.UserAlreadyExistException;
import com.rcore.domain.user.port.UserIdGenerator;
import com.rcore.domain.user.port.PasswordGenerator;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.userEmailVerification.port.UserCreateFromConfirmUseCase;

public class UserCreateFromConfirmUseCaseImpl implements UserCreateFromConfirmUseCase {

    private final UserRepository userRepository;
    private final PasswordGenerator passwordGenerator;
    private final UserIdGenerator userIdGenerator;

    public UserCreateFromConfirmUseCaseImpl(UserRepository userRepository, PasswordGenerator passwordGenerator, UserIdGenerator userIdGenerator) {
        this.userRepository = userRepository;
        this.passwordGenerator = passwordGenerator;
        this.userIdGenerator = userIdGenerator;
    }


    public UserEntity create(String email, String password) throws UserAlreadyExistException {
        userRepository.findByEmail(email)
                .orElseThrow(() -> new UserAlreadyExistException());

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userIdGenerator.generate());
        userEntity.setEmail(email);
        userEntity.setPassword(passwordGenerator.generate(userEntity.getId(), password));

        return userRepository.save(userEntity);
    }
}
