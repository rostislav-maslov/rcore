package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.base.usecase.AdminUseCase;
import com.rcore.domain.role.entity.Role;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.entity.UserStatus;
import com.rcore.domain.user.port.UserRepository;

class AdminBaseUseCase extends AdminUseCase {

    protected final UserRepository<UserEntity> userRepository;

    public AdminBaseUseCase(UserEntity actor, UserRepository userRepository, Role accessRole) throws AuthorizationException {
        super(actor, accessRole);
        this.userRepository = userRepository;
    }

}
