package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.entity.UserStatus;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.role.AdminUserBlockRole;

import java.util.Optional;


public class ActivateUseCase extends AdminBaseUseCase {

    public ActivateUseCase(UserEntity actor, UserRepository userRepository) throws AuthorizationException {
        super(actor, userRepository, new AdminUserBlockRole());
    }

    public UserEntity activate(UserEntity userEntity) {
        userEntity.setUserStatus(UserStatus.ACTIVE);
        userEntity.setFails(0);
        return userRepository.save(userEntity).get();

    }
}
