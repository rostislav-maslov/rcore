package com.rcore.domain.userEmailVerification.config;

import com.rcore.domain.userEmailVerification.port.UserEmailVerificationIdGenerator;
import com.rcore.domain.userEmailVerification.port.UserEmailVerificationRepository;
import com.rcore.domain.userEmailVerification.usecase.admin.UserEmailVerificationCreateUseCase;
import com.rcore.domain.userEmailVerification.usecase.admin.UserEmailVerificationDeleteUseCase;
import com.rcore.domain.userEmailVerification.usecase.admin.UserEmailVerificationUpdateUseCase;
import com.rcore.domain.userEmailVerification.usecase.admin.UserEmailVerificationViewUseCase;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;

public class UserEmailVerificationConfig {

    public static class Admin {
        protected final UserEmailVerificationRepository userEmailVerificationRepository;
        protected final UserEmailVerificationIdGenerator idGenerator;

        public Admin(UserEmailVerificationRepository userEmailVerificationRepository, UserEmailVerificationIdGenerator idGenerator) {
            this.userEmailVerificationRepository = userEmailVerificationRepository;
            this.idGenerator = idGenerator;
        }

        public UserEmailVerificationCreateUseCase createUseCase(UserEntity actor) throws AuthorizationException {
            return new UserEmailVerificationCreateUseCase(actor, this.userEmailVerificationRepository, idGenerator);
        }

        public UserEmailVerificationDeleteUseCase deleteUseCase(UserEntity actor) throws AuthorizationException {
            return new UserEmailVerificationDeleteUseCase(actor, this.userEmailVerificationRepository);
        }

        public UserEmailVerificationUpdateUseCase updateUseCase(UserEntity actor) throws AuthorizationException {
            return new UserEmailVerificationUpdateUseCase(actor, this.userEmailVerificationRepository);
        }

        public UserEmailVerificationViewUseCase viewUseCase(UserEntity actor) throws AuthorizationException {
            return new UserEmailVerificationViewUseCase(actor, this.userEmailVerificationRepository);
        }
    }

    public static class All {

        public All() {
        }

    }

    private final UserEmailVerificationRepository userEmailVerificationRepository;
    private final UserEmailVerificationIdGenerator idGenerator;

    public final Admin admin;
    public final All all;

    public UserEmailVerificationConfig(
            UserEmailVerificationRepository userEmailVerificationRepository,
            UserEmailVerificationIdGenerator idGenerator
    ) {
        this.userEmailVerificationRepository = userEmailVerificationRepository;
        this.idGenerator = idGenerator;

        this.admin = new Admin(this.userEmailVerificationRepository, this.idGenerator);
        this.all = new All();
    }

}
