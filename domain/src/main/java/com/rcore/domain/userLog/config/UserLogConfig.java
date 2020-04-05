package com.rcore.domain.userLog.config;

import com.rcore.domain.userLog.port.UserLogIdGenerator;
import com.rcore.domain.userLog.port.UserLogRepository;
import com.rcore.domain.userLog.usecase.admin.UserLogCreateUseCase;
import com.rcore.domain.userLog.usecase.admin.UserLogDeleteUseCase;
import com.rcore.domain.userLog.usecase.admin.UserLogUpdateUseCase;
import com.rcore.domain.userLog.usecase.admin.UserLogViewUseCase;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;

public class UserLogConfig {

    public static class Admin {
        protected final UserLogRepository userLogRepository;
        protected final UserLogIdGenerator idGenerator;

        public Admin(UserLogRepository userLogRepository, UserLogIdGenerator idGenerator) {
            this.userLogRepository = userLogRepository;
            this.idGenerator = idGenerator;
        }

        public UserLogCreateUseCase createUseCase(UserEntity actor) throws AuthorizationException {
            return new UserLogCreateUseCase(actor, this.userLogRepository, idGenerator);
        }

        public UserLogDeleteUseCase deleteUseCase(UserEntity actor) throws AuthorizationException {
            return new UserLogDeleteUseCase(actor, this.userLogRepository);
        }

        public UserLogUpdateUseCase updateUseCase(UserEntity actor) throws AuthorizationException {
            return new UserLogUpdateUseCase(actor, this.userLogRepository);
        }

        public UserLogViewUseCase viewUseCase(UserEntity actor) throws AuthorizationException {
            return new UserLogViewUseCase(actor, this.userLogRepository);
        }
    }

    public static class All {

        public All() {
        }

    }

    private final UserLogRepository userLogRepository;
    private final UserLogIdGenerator idGenerator;

    public final Admin admin;
    public final All all;

    public UserLogConfig(
            UserLogRepository userLogRepository,
            UserLogIdGenerator idGenerator
    ) {
        this.userLogRepository = userLogRepository;
        this.idGenerator = idGenerator;

        this.admin = new Admin(this.userLogRepository, this.idGenerator);
        this.all = new All();
    }

}
