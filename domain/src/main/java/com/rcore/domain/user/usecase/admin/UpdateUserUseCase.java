package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.UserAlreadyExistException;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.role.AdminUserBlockRole;

public class UpdateUserUseCase  extends AdminBaseUseCase {

    public UpdateUserUseCase(UserEntity actor, UserRepository userRepository)throws AuthorizationException {
        super(actor, userRepository, new AdminUserBlockRole());
    }

    public UserEntity update(UserEntity userEntity) throws UserAlreadyExistException {
        UserEntity old = userRepository.findById(userEntity.getId());

        old.setFirstName(userEntity.getFirstName());
        old.setLastName(userEntity.getLastName());
        old.setSecondName(userEntity.getSecondName());
        old.setFullName(userEntity.getFullName());

        return userRepository.save(old);
    }


}