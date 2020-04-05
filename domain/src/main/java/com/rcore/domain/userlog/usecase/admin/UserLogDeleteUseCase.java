package com.rcore.domain.userLog.usecase.admin;

import com.rcore.domain.userLog.entity.UserLogEntity;
import com.rcore.domain.userLog.port.UserLogRepository;
import com.rcore.domain.userLog.role.AdminUserLogDeleteRole;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;

public class UserLogDeleteUseCase  extends UserLogAdminBaseUseCase {

    public UserLogDeleteUseCase(UserEntity actor, UserLogRepository userLogRepository) throws AuthorizationException {
        super(actor, userLogRepository, new AdminUserLogDeleteRole());
    }

    public Boolean delete(UserLogEntity userLogEntity){
        userLogRepository.delete(userLogEntity);

        return true;
    }


}