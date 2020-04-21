package com.rcore.domain.userLog.usecase.admin;

import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.userLog.port.UserLogRepository;
import com.rcore.domain.userLog.access.AdminUserLogViewAccess;

public class UserLogUpdateUseCase extends UserLogAdminBaseUseCase {

    public UserLogUpdateUseCase(UserEntity actor, UserLogRepository userLogRepository) throws AuthorizationException {
        super(actor, userLogRepository, new AdminUserLogViewAccess());
    }
}
