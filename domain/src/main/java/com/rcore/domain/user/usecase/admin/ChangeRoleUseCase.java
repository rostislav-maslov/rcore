package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.role.entity.Role;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.role.AdminUserBlockRole;
import com.rcore.domain.user.role.AdminUserChangeRolesRole;

public class ChangeRoleUseCase extends AdminBaseUseCase {

    public ChangeRoleUseCase(UserEntity actor, UserRepository userRepository)throws AuthorizationException {
        super(actor, userRepository, new AdminUserChangeRolesRole());
    }

    public UserEntity remove(UserEntity userEntity, Role role) {
        userEntity.getRoles().remove(role);
        userEntity = userRepository.save(userEntity);

        return userEntity;
    }

    public UserEntity add(UserEntity userEntity, Role role) {
        userEntity.getRoles().add(role);
        userEntity = userRepository.save(userEntity);

        return userEntity;
    }
}