package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.entity.UserStatus;
import com.rcore.domain.user.exception.TokenExpiredException;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.access.AdminUserBlockAccess;


public class ActivateUseCase extends AdminBaseUseCase {

    public ActivateUseCase(UserRepository userRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase) {
        super(userRepository, new AdminUserBlockAccess(), authorizationByTokenUseCase);
    }

    public UserEntity activate(UserEntity userEntity) throws AuthenticationException, AuthorizationException, TokenExpiredException {
        checkAccess();

        userEntity.setStatus(UserStatus.ACTIVE);
        userEntity.setFails(0);
        return userRepository.save(userEntity);
    }
}
