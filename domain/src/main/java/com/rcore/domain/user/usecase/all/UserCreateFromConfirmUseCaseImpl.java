package com.rcore.domain.user.usecase.all;

import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.UserAlreadyExistException;
import com.rcore.domain.user.port.IdGenerator;
import com.rcore.domain.user.port.PasswordGenerator;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.userEmailVerification.port.UserCreateFromConfirmUseCase;

import java.util.Optional;

public class UserCreateFromConfirmUseCaseImpl implements UserCreateFromConfirmUseCase {

    private final UserRepository userRepository;
    private final PasswordGenerator passwordGenerator;
    private final IdGenerator idGenerator;

    public UserCreateFromConfirmUseCaseImpl(UserRepository userRepository, PasswordGenerator passwordGenerator, IdGenerator idGenerator) {
        this.userRepository = userRepository;
        this.passwordGenerator = passwordGenerator;
        this.idGenerator = idGenerator;
    }


    public Optional<UserEntity> create(String email, String password) throws UserAlreadyExistException {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(() -> new UserAlreadyExistException());

        userEntity = new UserEntity();
        userEntity.setEmail(email);
        userEntity.setPassword(passwordGenerator.generate(userEntity.getId(), password));

        return userRepository.save(userEntity);
    }
}
