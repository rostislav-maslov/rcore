package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.UserAlreadyExistException;
import com.rcore.domain.user.exception.UserNotFoundException;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.role.AdminUserUpdateRole;

import java.util.Date;

public class UpdateUserUseCase  extends AdminBaseUseCase {

    public UpdateUserUseCase(UserEntity actor, UserRepository userRepository)throws AuthorizationException {
        super(actor, userRepository, new AdminUserUpdateRole());
    }

    public UserEntity update(UserEntity userEntity) throws UserAlreadyExistException, UserNotFoundException {
        UserEntity old = userRepository.findById(userEntity.getId())
                .orElseThrow(() -> new UserNotFoundException());

        old.setFirstName(userEntity.getFirstName());
        old.setLastName(userEntity.getLastName());
        old.setSecondName(userEntity.getSecondName());
        old.setFullName(userEntity.getFullName());

        old.setUpdatedAt(new Date());

        return userRepository.save(old).get();
    }


}