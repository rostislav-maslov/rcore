package com.rcore.domain.userPasswordRecover.config;

import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.userPasswordRecover.port.UserPasswordRecoverIdGenerator;
import com.rcore.domain.userPasswordRecover.port.UserPasswordRecoverRepository;
import com.rcore.domain.userPasswordRecover.usecase.admin.UserPasswordRecoverDeleteUseCase;
import com.rcore.domain.userPasswordRecover.usecase.admin.UserPasswordRecoverViewUseCase;
import com.rcore.domain.token.exception.AuthorizationException;
import lombok.RequiredArgsConstructor;

public class UserPasswordRecoverConfig {

    @RequiredArgsConstructor
    public static class Admin {
        protected final UserPasswordRecoverRepository userPasswordRecoverRepository;
        protected final UserPasswordRecoverIdGenerator idGenerator;
        protected final AuthorizationByTokenUseCase authorizationByTokenUseCase;

        public UserPasswordRecoverDeleteUseCase deleteUseCase() throws AuthorizationException {
            return new UserPasswordRecoverDeleteUseCase(this.userPasswordRecoverRepository, authorizationByTokenUseCase);
        }

        public UserPasswordRecoverViewUseCase viewUseCase() throws AuthorizationException {
            return new UserPasswordRecoverViewUseCase(this.userPasswordRecoverRepository, authorizationByTokenUseCase);
        }
    }

    public static class All {

        public All() {
        }

    }

    private final UserPasswordRecoverRepository userPasswordRecoverRepository;
    private final UserPasswordRecoverIdGenerator idGenerator;
    private final AuthorizationByTokenUseCase authorizationByTokenUseCase;

    public final Admin admin;
    public final All all;

    public UserPasswordRecoverConfig(
            UserPasswordRecoverRepository userPasswordRecoverRepository,
            UserPasswordRecoverIdGenerator idGenerator,
            AuthorizationByTokenUseCase authorizationByTokenUseCase) {
        this.userPasswordRecoverRepository = userPasswordRecoverRepository;
        this.idGenerator = idGenerator;
        this.authorizationByTokenUseCase = authorizationByTokenUseCase;

        this.admin = new Admin(this.userPasswordRecoverRepository, this.idGenerator, this.authorizationByTokenUseCase);
        this.all = new All();
    }

}
