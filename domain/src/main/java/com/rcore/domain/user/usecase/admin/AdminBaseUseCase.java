package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.base.usecase.AdminUseCase;
import com.rcore.domain.access.entity.Access;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.port.UserRepository;

class AdminBaseUseCase extends AdminUseCase {

    protected final UserRepository userRepository;

    public AdminBaseUseCase(UserEntity actor, UserRepository userRepository, Access accessAccess) throws AuthorizationException {
        super(actor, accessAccess);
        this.userRepository = userRepository;
    }

}
