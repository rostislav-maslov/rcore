package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.access.entity.Access;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.TokenExpiredException;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.access.AdminUserChangeRolesAccess;

import java.time.LocalDateTime;

public class ChangeRoleUseCase extends AdminBaseUseCase {

    public ChangeRoleUseCase(UserRepository userRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase) {
        super(userRepository, new AdminUserChangeRolesAccess(), authorizationByTokenUseCase);
    }

    public UserEntity remove(UserEntity userEntity, Access access) throws AuthenticationException, AuthorizationException, TokenExpiredException {
        checkAccess();

        userEntity.getAccesses().remove(access);
        userEntity.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(userEntity);
    }

    public UserEntity add(UserEntity userEntity, Access access) throws AuthenticationException, AuthorizationException, TokenExpiredException {
        checkAccess();

        userEntity.getAccesses().add(access);
        userEntity.setUpdatedAt(LocalDateTime.now());
        return userEntity;
    }
}