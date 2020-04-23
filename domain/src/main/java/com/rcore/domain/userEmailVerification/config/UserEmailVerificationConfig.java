package com.rcore.domain.userEmailVerification.config;

import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.userEmailVerification.port.UserEmailVerificationIdGenerator;
import com.rcore.domain.userEmailVerification.port.UserEmailVerificationRepository;
import com.rcore.domain.userEmailVerification.usecase.admin.UserEmailVerificationCreateUseCase;
import com.rcore.domain.userEmailVerification.usecase.admin.UserEmailVerificationDeleteUseCase;
import com.rcore.domain.userEmailVerification.usecase.admin.UserEmailVerificationUpdateUseCase;
import com.rcore.domain.userEmailVerification.usecase.admin.UserEmailVerificationViewUseCase;
import com.rcore.domain.token.exception.AuthorizationException;
import lombok.RequiredArgsConstructor;

public class UserEmailVerificationConfig {

    @RequiredArgsConstructor
    public static class Admin {
        protected final UserEmailVerificationRepository userEmailVerificationRepository;
        protected final UserEmailVerificationIdGenerator idGenerator;
        protected final AuthorizationByTokenUseCase authorizationByTokenUseCase;

        public UserEmailVerificationCreateUseCase createUseCase() throws AuthorizationException {
            return new UserEmailVerificationCreateUseCase(this.userEmailVerificationRepository, idGenerator, authorizationByTokenUseCase);
        }

        public UserEmailVerificationDeleteUseCase deleteUseCase() throws AuthorizationException {
            return new UserEmailVerificationDeleteUseCase(this.userEmailVerificationRepository, authorizationByTokenUseCase);
        }

        public UserEmailVerificationUpdateUseCase updateUseCase() throws AuthorizationException {
            return new UserEmailVerificationUpdateUseCase(this.userEmailVerificationRepository, authorizationByTokenUseCase);
        }

        public UserEmailVerificationViewUseCase viewUseCase() throws AuthorizationException {
            return new UserEmailVerificationViewUseCase(this.userEmailVerificationRepository, authorizationByTokenUseCase);
        }
    }

    public static class All {

        public All() {
        }

    }

    private final UserEmailVerificationRepository userEmailVerificationRepository;
    private final UserEmailVerificationIdGenerator idGenerator;
    private final AuthorizationByTokenUseCase authorizationByTokenUseCase;

    public final Admin admin;
    public final All all;

    public UserEmailVerificationConfig(
            UserEmailVerificationRepository userEmailVerificationRepository,
            UserEmailVerificationIdGenerator idGenerator,
            AuthorizationByTokenUseCase authorizationByTokenUseCase) {
        this.userEmailVerificationRepository = userEmailVerificationRepository;
        this.idGenerator = idGenerator;
        this.authorizationByTokenUseCase = authorizationByTokenUseCase;

        this.admin = new Admin(this.userEmailVerificationRepository, this.idGenerator, this.authorizationByTokenUseCase);
        this.all = new All();
    }

}
