package com.rcore.domain.userEmailVerification.port;

import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.UserAlreadyExistException;

public interface UserCreateFromConfirmUseCase {
    UserEntity create(String email, String password) throws UserAlreadyExistException;
}
