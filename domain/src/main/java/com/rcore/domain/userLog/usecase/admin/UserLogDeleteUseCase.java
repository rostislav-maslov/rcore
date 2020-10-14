package com.rcore.domain.userLog.usecase.admin;

import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.exception.TokenExpiredException;
import com.rcore.domain.userLog.entity.UserLogEntity;
import com.rcore.domain.userLog.port.UserLogRepository;
import com.rcore.domain.userLog.access.AdminUserLogDeleteAccess;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;

public class UserLogDeleteUseCase  extends UserLogAdminBaseUseCase {

    public UserLogDeleteUseCase(UserLogRepository userLogRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase){
        super(userLogRepository, new AdminUserLogDeleteAccess(), authorizationByTokenUseCase);
    }

    public Boolean delete(UserLogEntity userLogEntity) throws AuthenticationException, AuthorizationException, TokenExpiredException {
        checkAccess();
        userLogRepository.delete(userLogEntity);

        return true;
    }


}