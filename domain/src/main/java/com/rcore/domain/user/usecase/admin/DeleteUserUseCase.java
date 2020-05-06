package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.UserAlreadyExistException;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.access.AdminUserDeleteAccess;

public class DeleteUserUseCase extends AdminBaseUseCase {

    public DeleteUserUseCase(UserRepository userRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase) {
        super(userRepository, new AdminUserDeleteAccess(), authorizationByTokenUseCase);
    }

    public Boolean delete(UserEntity userEntity) throws AuthenticationException, AuthorizationException {
        checkAccess();
        return userRepository.delete(userEntity);
    }

    public Boolean deleteById(String id) throws AuthenticationException, AuthorizationException {
        checkAccess();

        return userRepository.deleteById(id);
    }


}