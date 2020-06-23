package com.rcore.domain.userPasswordRecover.config;

import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.port.PasswordGenerator;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.userPasswordRecover.port.EmailSender;
import com.rcore.domain.userPasswordRecover.port.UserPasswordRecoverIdGenerator;
import com.rcore.domain.userPasswordRecover.port.UserPasswordRecoverRepository;
import com.rcore.domain.userPasswordRecover.usecase.admin.UserPasswordRecoverDeleteUseCase;
import com.rcore.domain.userPasswordRecover.usecase.admin.UserPasswordRecoverViewUseCase;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.userPasswordRecover.usecase.client.UserPasswordRecoverConfirmUseCase;
import com.rcore.domain.userPasswordRecover.usecase.client.UserPasswordRecoverCreateUseCase;
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

    @RequiredArgsConstructor
    public static class All {

        protected final UserPasswordRecoverRepository userPasswordRecoverRepository;
        protected final UserRepository userRepository;
        protected final UserPasswordRecoverIdGenerator userPasswordRecoverIdGenerator;
        protected final EmailSender emailSender;
        protected final PasswordGenerator passwordGenerator;

        public UserPasswordRecoverCreateUseCase createUseCase(){
            return new UserPasswordRecoverCreateUseCase(this.userPasswordRecoverRepository, this.userRepository, this.userPasswordRecoverIdGenerator, this.emailSender);
        }

        public UserPasswordRecoverConfirmUseCase confirmUseCase(){
            return new UserPasswordRecoverConfirmUseCase(this.userPasswordRecoverRepository, this.userRepository, this.passwordGenerator);
        }

    }

    private final UserPasswordRecoverRepository userPasswordRecoverRepository;
    private final UserPasswordRecoverIdGenerator idGenerator;
    private final AuthorizationByTokenUseCase authorizationByTokenUseCase;
    protected final UserRepository userRepository;
    protected final EmailSender emailSender;
    protected final PasswordGenerator passwordGenerator;

    public final Admin admin;
    public final All all;

    public UserPasswordRecoverConfig(
            UserPasswordRecoverRepository userPasswordRecoverRepository,
            UserPasswordRecoverIdGenerator idGenerator,
            AuthorizationByTokenUseCase authorizationByTokenUseCase,
            UserRepository userRepository,
            EmailSender emailSender,
            PasswordGenerator passwordGenerator) {
        this.userPasswordRecoverRepository = userPasswordRecoverRepository;
        this.idGenerator = idGenerator;
        this.authorizationByTokenUseCase = authorizationByTokenUseCase;
        this.userRepository = userRepository;
        this.emailSender = emailSender;
        this.passwordGenerator = passwordGenerator;

        this.admin = new Admin(this.userPasswordRecoverRepository, this.idGenerator, this.authorizationByTokenUseCase);
        this.all = new All(this.userPasswordRecoverRepository, this.userRepository, this.idGenerator, this.emailSender, this.passwordGenerator);
    }

}
