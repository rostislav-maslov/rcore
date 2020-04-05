package com.rcore.domain.userLog.usecase.admin;

import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.userLog.entity.UserLogEntity;
import com.rcore.domain.userLog.port.UserLogRepository;
import com.rcore.domain.userLog.role.AdminUserLogViewRole;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;

public class UserLogViewUseCase extends UserLogAdminBaseUseCase {

    public UserLogViewUseCase(UserEntity actor, UserLogRepository userLogRepository) throws AuthorizationException {
        super(actor, userLogRepository, new AdminUserLogViewRole());
    }

    public UserLogEntity findById(String id) {
        return userLogRepository.findById(id);
    }

    public UserLogEntity search(String id) {
        return userLogRepository.findById(id);
    }

    public SearchResult<UserLogEntity> find(Long size, Long skip) {
        return userLogRepository.find(size, skip);
    }

}