package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.base.port.SearchRequest;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.TokenExpiredException;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.access.AdminUserViewAccess;

import java.util.Optional;

public class ViewUserUseCase extends AdminBaseUseCase {

    public ViewUserUseCase(UserRepository userRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase) {
        super(userRepository, new AdminUserViewAccess(), authorizationByTokenUseCase);
    }

    public Optional<UserEntity> findById(String id) throws AuthenticationException, AuthorizationException, TokenExpiredException {
        checkAccess();
        return userRepository.findById(id);
    }

    public SearchResult<UserEntity> find(SearchRequest request) throws AuthenticationException, AuthorizationException, TokenExpiredException {
        checkAccess();
        return userRepository.find(request);
    }

    public SearchResult<UserEntity> findWithSearch(SearchRequest request, String roleId) throws AuthenticationException, AuthorizationException, TokenExpiredException {
        checkAccess();
        return userRepository.findWithFilters(request, roleId);
    }

}