package com.rcore.domain.userLog.config;

import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.userLog.port.UserLogIdGenerator;
import com.rcore.domain.userLog.port.UserLogRepository;
import com.rcore.domain.userLog.usecase.admin.UserLogCreateUseCase;
import com.rcore.domain.userLog.usecase.admin.UserLogDeleteUseCase;
import com.rcore.domain.userLog.usecase.admin.UserLogUpdateUseCase;
import com.rcore.domain.userLog.usecase.admin.UserLogViewUseCase;
import com.rcore.domain.token.exception.AuthorizationException;
import lombok.RequiredArgsConstructor;

public class UserLogConfig {

    @RequiredArgsConstructor
    public static class Admin {
        protected final UserLogRepository userLogRepository;
        protected final UserLogIdGenerator idGenerator;
        protected final AuthorizationByTokenUseCase authorizationByTokenUseCase;

        public UserLogCreateUseCase createUseCase() throws AuthorizationException {
            return new UserLogCreateUseCase(this.userLogRepository, this.authorizationByTokenUseCase);
        }

        public UserLogDeleteUseCase deleteUseCase() throws AuthorizationException {
            return new UserLogDeleteUseCase(this.userLogRepository, this.authorizationByTokenUseCase);
        }

        public UserLogUpdateUseCase updateUseCase() throws AuthorizationException {
            return new UserLogUpdateUseCase(this.userLogRepository, this.authorizationByTokenUseCase);
        }

        public UserLogViewUseCase viewUseCase() throws AuthorizationException {
            return new UserLogViewUseCase(this.userLogRepository, this.authorizationByTokenUseCase);
        }
    }

    public static class All {

        public All() {
        }

    }

    private final UserLogRepository userLogRepository;
    private final UserLogIdGenerator idGenerator;
    private final AuthorizationByTokenUseCase authorizationByTokenUseCase;

    public final Admin admin;
    public final All all;

    public UserLogConfig(
            UserLogRepository userLogRepository,
            UserLogIdGenerator idGenerator,
            AuthorizationByTokenUseCase authorizationByTokenUseCase) {
        this.userLogRepository = userLogRepository;
        this.idGenerator = idGenerator;
        this.authorizationByTokenUseCase = authorizationByTokenUseCase;

        this.admin = new Admin(this.userLogRepository, this.idGenerator, this.authorizationByTokenUseCase);
        this.all = new All();
    }

}
