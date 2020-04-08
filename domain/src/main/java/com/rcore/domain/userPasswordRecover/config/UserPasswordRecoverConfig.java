package com.rcore.domain.userPasswordRecover.config;

import com.rcore.domain.userPasswordRecover.port.UserPasswordRecoverIdGenerator;
import com.rcore.domain.userPasswordRecover.port.UserPasswordRecoverRepository;
import com.rcore.domain.userPasswordRecover.usecase.admin.UserPasswordRecoverCreateUseCase;
import com.rcore.domain.userPasswordRecover.usecase.admin.UserPasswordRecoverDeleteUseCase;
import com.rcore.domain.userPasswordRecover.usecase.admin.UserPasswordRecoverUpdateUseCase;
import com.rcore.domain.userPasswordRecover.usecase.admin.UserPasswordRecoverViewUseCase;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;

public class UserPasswordRecoverConfig {

    public static class Admin {
        protected final UserPasswordRecoverRepository userPasswordRecoverRepository;
        protected final UserPasswordRecoverIdGenerator idGenerator;

        public Admin(UserPasswordRecoverRepository userPasswordRecoverRepository, UserPasswordRecoverIdGenerator idGenerator) {
            this.userPasswordRecoverRepository = userPasswordRecoverRepository;
            this.idGenerator = idGenerator;
        }

        public UserPasswordRecoverCreateUseCase createUseCase(UserEntity actor) throws AuthorizationException {
            return new UserPasswordRecoverCreateUseCase(actor, this.userPasswordRecoverRepository, idGenerator);
        }

        public UserPasswordRecoverDeleteUseCase deleteUseCase(UserEntity actor) throws AuthorizationException {
            return new UserPasswordRecoverDeleteUseCase(actor, this.userPasswordRecoverRepository);
        }

        public UserPasswordRecoverUpdateUseCase updateUseCase(UserEntity actor) throws AuthorizationException {
            return new UserPasswordRecoverUpdateUseCase(actor, this.userPasswordRecoverRepository);
        }

        public UserPasswordRecoverViewUseCase viewUseCase(UserEntity actor) throws AuthorizationException {
            return new UserPasswordRecoverViewUseCase(actor, this.userPasswordRecoverRepository);
        }
    }

    public static class All {

        public All() {
        }

    }

    private final UserPasswordRecoverRepository userPasswordRecoverRepository;
    private final UserPasswordRecoverIdGenerator idGenerator;

    public final Admin admin;
    public final All all;

    public UserPasswordRecoverConfig(
            UserPasswordRecoverRepository userPasswordRecoverRepository,
            UserPasswordRecoverIdGenerator idGenerator
    ) {
        this.userPasswordRecoverRepository = userPasswordRecoverRepository;
        this.idGenerator = idGenerator;

        this.admin = new Admin(this.userPasswordRecoverRepository, this.idGenerator);
        this.all = new All();
    }

}
