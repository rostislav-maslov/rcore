package com.rcore.domain.userPasswordRecover.usecase.client;

import com.rcore.domain.userPasswordRecover.port.UserPasswordRecoverRepository;

class UserPasswordRecoverBaseUseCase{

    protected final UserPasswordRecoverRepository userPasswordRecoverRepository;

    public UserPasswordRecoverBaseUseCase(UserPasswordRecoverRepository userPasswordRecoverRepository) {
        this.userPasswordRecoverRepository = userPasswordRecoverRepository;
    }

}
