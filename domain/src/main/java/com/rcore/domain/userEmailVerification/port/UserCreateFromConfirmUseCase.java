package com.rcore.domain.userEmailVerification.port;

import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.UserAlreadyExistException;

import java.util.Optional;

public interface UserCreateFromConfirmUseCase {
    Optional<UserEntity> create(String email, String password) throws UserAlreadyExistException;
}
