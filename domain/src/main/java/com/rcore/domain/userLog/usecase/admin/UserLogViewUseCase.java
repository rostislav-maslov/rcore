package com.rcore.domain.userLog.usecase.admin;

import com.rcore.domain.base.port.SearchRequest;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.exception.TokenExpiredException;
import com.rcore.domain.userLog.access.AdminUserLogViewAccess;
import com.rcore.domain.userLog.entity.UserLogEntity;
import com.rcore.domain.userLog.port.UserLogRepository;

import java.util.Optional;

public class UserLogViewUseCase extends UserLogAdminBaseUseCase {

    public UserLogViewUseCase(UserLogRepository userLogRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase) {
        super(userLogRepository, new AdminUserLogViewAccess(), authorizationByTokenUseCase);
    }

    public Optional<UserLogEntity> findById(String id) throws AuthenticationException, AuthorizationException, TokenExpiredException {
        checkAccess();
        return userLogRepository.findById(id);
    }

    public Optional<UserLogEntity> search(String id) throws AuthenticationException, AuthorizationException, TokenExpiredException {
        checkAccess();
        return userLogRepository.findById(id);
    }

    public SearchResult<UserLogEntity> find(SearchRequest request) throws AuthenticationException, AuthorizationException, TokenExpiredException {
        checkAccess();
        return userLogRepository.find(request);
    }

}