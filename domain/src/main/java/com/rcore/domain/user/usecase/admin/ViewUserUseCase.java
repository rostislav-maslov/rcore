package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.access.AdminUserViewAccess;

import java.util.Optional;

public class ViewUserUseCase extends AdminBaseUseCase {

    public ViewUserUseCase(UserRepository userRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase) {
        super(userRepository, new AdminUserViewAccess(), authorizationByTokenUseCase);
    }

    public Optional<UserEntity> findById(String id) throws AuthenticationException, AuthorizationException {
        checkAccess();
        return userRepository.findById(id);
    }

    public SearchResult<UserEntity> find(Long size, Long skip) throws AuthenticationException, AuthorizationException {
        checkAccess();
        return userRepository.find(size, skip);
    }

}