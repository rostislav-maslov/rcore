package com.rcore.domain.user;

import com.rcore.domain.database.memory.role.RoleRepositoryImpl;
import com.rcore.domain.database.memory.token.port.*;
import com.rcore.domain.database.memory.user.port.UserIdGeneratorImpl;
import com.rcore.domain.database.memory.user.port.UserRepositoryImpl;
import com.rcore.domain.role.port.RoleRepository;
import com.rcore.domain.token.port.AccessTokenStorage;
import com.rcore.domain.token.port.RefreshTokenRepository;
import com.rcore.domain.token.port.RefreshTokenStorage;
import com.rcore.domain.token.port.impl.TokenSaltGeneratorImpl;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.token.usecase.CreateAccessTokenUseCase;
import com.rcore.domain.token.usecase.CreateRefreshTokenUseCase;
import com.rcore.domain.token.usecase.ExpireTokenUseCase;
import com.rcore.domain.user.config.UserConfig;
import com.rcore.domain.user.port.UserIdGenerator;
import com.rcore.domain.user.port.PasswordGenerator;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.port.impl.PasswordGeneratorImpl;
import lombok.Getter;

@Getter
public class UserAppConfig {
    private final UserConfig userConfig;
    private final UserRepository userRepository;
    private final UserIdGenerator userIdGenerator;
    private final PasswordGenerator passwordGenerator;
    private final ExpireTokenUseCase expireTokenUseCase;
    private final CreateRefreshTokenUseCase createRefreshTokenUseCase;
    private final CreateAccessTokenUseCase createAccessTokenUseCase;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RoleRepository roleRepository;
    private final AuthorizationByTokenUseCase authorizationByTokenUseCase;
    private final AccessTokenStorage accessTokenStorage;
    private final RefreshTokenStorage refreshTokenStorage;

    public UserAppConfig() {
        this.accessTokenStorage = new AccessTokenStorageImpl();
        this.refreshTokenStorage = new RefreshTokenStorageImpl();
        this.userRepository = new UserRepositoryImpl();
        this.userIdGenerator = new UserIdGeneratorImpl();
        this.passwordGenerator = new PasswordGeneratorImpl();
        this.refreshTokenRepository = new RefreshTokenRepositoryImpl();
        this.expireTokenUseCase = new ExpireTokenUseCase(refreshTokenRepository);
        this.createRefreshTokenUseCase = new CreateRefreshTokenUseCase(new RefreshTokenIdGeneratorImpl(), refreshTokenStorage, new TokenSaltGeneratorImpl());
        this.createAccessTokenUseCase = new CreateAccessTokenUseCase(new AccessTokenIdGeneratorImpl(), accessTokenStorage, createRefreshTokenUseCase);
        this.roleRepository = new RoleRepositoryImpl();
        this.authorizationByTokenUseCase = new AuthorizationByTokenUseCase(this.accessTokenStorage, refreshTokenStorage, this.userRepository);

        this.userConfig = new UserConfig(userRepository, userIdGenerator, passwordGenerator, expireTokenUseCase, createRefreshTokenUseCase, createAccessTokenUseCase, refreshTokenRepository, roleRepository, this.authorizationByTokenUseCase, accessTokenStorage);
    }
}
