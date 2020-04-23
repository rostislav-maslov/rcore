package com.rcore.domain.userLog.usecase.admin;

import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.userLog.entity.UserLogEntity;
import com.rcore.domain.userLog.port.UserLogRepository;
import com.rcore.domain.userLog.access.AdminUserLogViewAccess;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;

import java.util.Optional;

public class UserLogViewUseCase extends UserLogAdminBaseUseCase {

    public UserLogViewUseCase(UserLogRepository userLogRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase) {
        super(userLogRepository, new AdminUserLogViewAccess(), authorizationByTokenUseCase);
    }

    public Optional<UserLogEntity> findById(String id) throws AuthenticationException, AuthorizationException {
        checkAccess();
        return userLogRepository.findById(id);
    }

    public Optional<UserLogEntity> search(String id) throws AuthenticationException, AuthorizationException {
        checkAccess();
        return userLogRepository.findById(id);
    }

    public SearchResult<UserLogEntity> find(Long size, Long skip) throws AuthenticationException, AuthorizationException {
        checkAccess();
        return userLogRepository.find(size, skip);
    }

}