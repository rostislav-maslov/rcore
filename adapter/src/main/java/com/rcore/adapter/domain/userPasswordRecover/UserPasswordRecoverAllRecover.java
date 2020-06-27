package com.rcore.adapter.domain.userPasswordRecover;

import com.rcore.domain.user.exception.UserNotFoundException;
import com.rcore.domain.userPasswordRecover.config.UserPasswordRecoverConfig;
import com.rcore.domain.userPasswordRecover.exception.UserPasswordRecoverNotFoundException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserPasswordRecoverAllRecover {
    private final UserPasswordRecoverConfig userPasswordRecoverConfig;

    public void createUseCase(String email) throws UserNotFoundException {
        userPasswordRecoverConfig.all.createUseCase()
                .create(email);
    }

    public void createUseCase(String email, String code, String newPassword) throws UserPasswordRecoverNotFoundException, UserNotFoundException {
        userPasswordRecoverConfig.all.confirmUseCase()
                .confirm(email, code, newPassword);
    }


}
