package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.UserAlreadyExistException;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.role.AdminUserBlockRole;

public class DeleteUserUseCase  extends AdminBaseUseCase {

    public DeleteUserUseCase(UserEntity actor, UserRepository userRepository) throws AuthorizationException {
        super(actor, userRepository, new AdminUserBlockRole());
    }

    public Boolean delete(UserEntity userEntity) throws UserAlreadyExistException {
        userRepository.delete(userEntity);

        return true;
    }


}