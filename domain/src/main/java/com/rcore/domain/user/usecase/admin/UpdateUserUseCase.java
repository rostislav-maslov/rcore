package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.UserAlreadyExistException;
import com.rcore.domain.user.exception.UserNotFoundException;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.access.AdminUserUpdateAccess;

import java.time.LocalDateTime;

public class UpdateUserUseCase  extends AdminBaseUseCase {

    public UpdateUserUseCase(UserRepository userRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase)throws AuthorizationException {
        super(userRepository, new AdminUserUpdateAccess(), authorizationByTokenUseCase);
    }

    public UserEntity update(UserEntity userEntity) throws UserNotFoundException, AuthenticationException, AuthorizationException {
        checkAccess();

        UserEntity old = userRepository.findById(userEntity.getId())
                .orElseThrow(() -> new UserNotFoundException());

        old.setFirstName(userEntity.getFirstName());
        old.setLastName(userEntity.getLastName());
        old.setSecondName(userEntity.getSecondName());
        old.setFullName(userEntity.getFullName());

        old.setUpdatedAt(LocalDateTime.now());

        return userRepository.save(old);
    }


}