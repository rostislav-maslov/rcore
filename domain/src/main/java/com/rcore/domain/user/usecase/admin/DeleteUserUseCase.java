package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.UserAlreadyExistException;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.role.AdminUserBlockRole;
import com.rcore.domain.user.role.AdminUserDeleteRole;

public class DeleteUserUseCase  extends AdminBaseUseCase {

    public DeleteUserUseCase(UserEntity actor, UserRepository userRepository) throws AuthorizationException {
        super(actor, userRepository, new AdminUserDeleteRole());
    }

    public Boolean delete(UserEntity userEntity) throws UserAlreadyExistException {
        return userRepository.delete(userEntity);
    }


}