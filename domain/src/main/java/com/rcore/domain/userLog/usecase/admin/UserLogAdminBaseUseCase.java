package com.rcore.domain.userLog.usecase.admin;

import com.rcore.domain.base.usecase.AdminUseCase;
import com.rcore.domain.access.entity.Access;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.userLog.port.UserLogRepository;

class UserLogAdminBaseUseCase extends AdminUseCase {

    protected final UserLogRepository userLogRepository;

    public UserLogAdminBaseUseCase(UserEntity actor, UserLogRepository userLogRepository, Access accessAccess) throws AuthorizationException {
        super(actor, accessAccess);
        this.userLogRepository = userLogRepository;
    }

}
