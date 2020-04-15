package com.rcore.domain.user.config;

import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.port.RefreshTokenRepository;
import com.rcore.domain.token.usecase.CreateAccessTokenUseCase;
import com.rcore.domain.token.usecase.CreateRefreshTokenUseCase;
import com.rcore.domain.token.usecase.ExpireTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.port.UserIdGenerator;
import com.rcore.domain.user.port.PasswordGenerator;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.usecase.admin.*;
import com.rcore.domain.user.usecase.all.EmailAuthenticationUseCase;
import com.rcore.domain.user.usecase.all.ViewUserUserCase;

public class UserConfig {

    public static class Admin {
        protected final UserRepository userRepository;
        protected final UserIdGenerator userIdGenerator;
        protected final PasswordGenerator passwordGenerator;
        protected final ExpireTokenUseCase expireTokenUseCase;

        public Admin(UserRepository userRepository, UserIdGenerator userIdGenerator, PasswordGenerator passwordGenerator, ExpireTokenUseCase expireTokenUseCase) {
            this.userRepository = userRepository;
            this.userIdGenerator = userIdGenerator;
            this.passwordGenerator = passwordGenerator;
            this.expireTokenUseCase = expireTokenUseCase;
        }

        public ActivateUseCase activateUseCase(UserEntity actor) throws AuthorizationException {
            return new ActivateUseCase(actor, this.userRepository);
        }

        public BlockUseCase BlockUseCase(UserEntity actor) throws AuthorizationException {
            return new BlockUseCase(actor, this.userRepository, expireTokenUseCase);
        }

        public ChangeRoleUseCase ChangeRoleUseCase(UserEntity actor) throws AuthorizationException {
            return new ChangeRoleUseCase(actor, this.userRepository);
        }

        public CreateUseCase CreateUseCase(UserEntity actor) throws AuthorizationException {
            return new CreateUseCase(actor, this.userRepository, passwordGenerator, userIdGenerator);
        }

        public DeleteUserUseCase DeleteUserUseCase(UserEntity actor) throws AuthorizationException {
            return new DeleteUserUseCase(actor, this.userRepository);
        }

        public InitAdminUseCase InitAdminUseCase() throws AuthorizationException {
            return new InitAdminUseCase(this.userRepository, passwordGenerator, userIdGenerator);
        }

        public UpdateUserUseCase UpdateUserUseCase(UserEntity actor) throws AuthorizationException {
            return new UpdateUserUseCase(actor, this.userRepository);
        }

        public ViewUserUseCase ViewUserUseCase(UserEntity actor) throws AuthorizationException {
            return new ViewUserUseCase(actor, this.userRepository);
        }
    }

    public static class All {
        private final UserRepository userRepository;
        private final PasswordGenerator passwordGenerator;
        private final CreateRefreshTokenUseCase createRefreshTokenUseCase;
        private final CreateAccessTokenUseCase createAccessTokenUseCase;
        private final RefreshTokenRepository refreshTokenRepository;

        public All(UserRepository userRepository, PasswordGenerator passwordGenerator, CreateRefreshTokenUseCase createRefreshTokenUseCase, CreateAccessTokenUseCase createAccessTokenUseCase, RefreshTokenRepository refreshTokenRepository) {
            this.userRepository = userRepository;
            this.passwordGenerator = passwordGenerator;
            this.createRefreshTokenUseCase = createRefreshTokenUseCase;
            this.createAccessTokenUseCase = createAccessTokenUseCase;
            this.refreshTokenRepository = refreshTokenRepository;
        }

        public EmailAuthenticationUseCase emailAuthenticationUseCase() {
            return new EmailAuthenticationUseCase(userRepository, passwordGenerator, createRefreshTokenUseCase, createAccessTokenUseCase, refreshTokenRepository);
        }

        public ViewUserUserCase viewUserUserCase() {
            return new ViewUserUserCase(userRepository);
        }

    }

    private final UserRepository userRepository;
    private final UserIdGenerator userIdGenerator;
    private final PasswordGenerator passwordGenerator;
    private final ExpireTokenUseCase expireTokenUseCase;
    private final CreateRefreshTokenUseCase createRefreshTokenUseCase;
    private final CreateAccessTokenUseCase createAccessTokenUseCase;
    private final RefreshTokenRepository refreshTokenRepository;

    public final Admin admin;
    public final All all;

    public UserConfig(
            UserRepository userRepository,
            UserIdGenerator userIdGenerator,
            PasswordGenerator passwordGenerator,
            ExpireTokenUseCase expireTokenUseCase,
            CreateRefreshTokenUseCase createRefreshTokenUseCase,
            CreateAccessTokenUseCase createAccessTokenUseCase,
            RefreshTokenRepository refreshTokenRepository) {
        this.userRepository = userRepository;
        this.userIdGenerator = userIdGenerator;
        this.passwordGenerator = passwordGenerator;
        this.expireTokenUseCase = expireTokenUseCase;
        this.createRefreshTokenUseCase = createRefreshTokenUseCase;
        this.createAccessTokenUseCase = createAccessTokenUseCase;
        this.refreshTokenRepository = refreshTokenRepository;

        this.admin = new Admin(this.userRepository, this.userIdGenerator, this.passwordGenerator, this.expireTokenUseCase);
        this.all = new All(this.userRepository, this.passwordGenerator, this.createRefreshTokenUseCase, this.createAccessTokenUseCase, this.refreshTokenRepository);
    }

}
