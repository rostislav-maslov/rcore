package com.rcore.domain.base;

import com.rcore.domain.token.port.RefreshTokenRepository;
import com.rcore.domain.token.port.impl.TokenSaltGeneratorImpl;
import com.rcore.domain.token.usecase.CreateAccessTokenUseCase;
import com.rcore.domain.token.usecase.CreateRefreshTokenUseCase;
import com.rcore.domain.token.usecase.ExpireTokenUseCase;
import com.rcore.domain.user.port.IdGenerator;
import com.rcore.domain.user.port.PasswordGenerator;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.config.UserConfig;
import com.rcore.domain.user.port.impl.PasswordGeneratorImpl;
import lombok.Getter;

@Getter
public class TestAppConfig {
//    private final UserConfig userConfig;
//    private final UserRepository userRepository;
//    private final IdGenerator idGenerator;
//    private final PasswordGenerator passwordGenerator;
//    private final ExpireTokenUseCase expireTokenUseCase;
//    private final CreateRefreshTokenUseCase createRefreshTokenUseCase;
//    private final CreateAccessTokenUseCase createAccessTokenUseCase;
//    private final RefreshTokenRepository refreshTokenRepository;

    public TestAppConfig() {
//        this.userRepository = new UserRepositoryImpl();
//        this.idGenerator = new UserIdGeneratorImpl();
//        this.passwordGenerator = new PasswordGeneratorImpl();
//        this.refreshTokenRepository = new RefreshTokenRepositoryImpl();
//        this.expireTokenUseCase = new ExpireTokenUseCase(refreshTokenRepository);
//        this.createRefreshTokenUseCase = new CreateRefreshTokenUseCase(new RefreshTokenIdGeneratorImpl(), refreshTokenRepository, new TokenSaltGeneratorImpl());
//        this.createAccessTokenUseCase = new CreateAccessTokenUseCase(new AccessTokenIdGeneratorImpl(), createRefreshTokenUseCase);
//
//
//        this.userConfig = new UserConfig(userRepository, idGenerator, passwordGenerator, expireTokenUseCase, createRefreshTokenUseCase, createAccessTokenUseCase, refreshTokenRepository);
    }
}
