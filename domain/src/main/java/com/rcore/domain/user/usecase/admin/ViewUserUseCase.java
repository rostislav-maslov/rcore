package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.role.AdminUserBlockRole;

public class ViewUserUseCase extends AdminBaseUseCase {

    public ViewUserUseCase(UserEntity actor, UserRepository userRepository) throws AuthorizationException {
        super(actor, userRepository, new AdminUserBlockRole());
    }

    public UserEntity findById(String id) {
        return userRepository.findById(id);
    }

    public UserEntity search(String id) {
        return userRepository.findById(id);
    }


}