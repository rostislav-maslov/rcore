package com.rcore.domain.token.config;

import com.rcore.domain.token.port.*;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.token.usecase.CreateAccessTokenUseCase;
import com.rcore.domain.token.usecase.CreateRefreshTokenUseCase;
import com.rcore.domain.token.usecase.ExpireTokenUseCase;
import com.rcore.domain.user.port.UserRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
public class TokenConfig {

    @RequiredArgsConstructor
    @Getter
    public static class All {

        protected final RefreshTokenRepository refreshTokenRepository;
        protected final AccessTokenStorage accessTokenStorage;
        protected final UserRepository userRepository;
        protected final AccessTokenIdGenerator accessTokenIdGenerator;
        protected final RefreshTokenIdGenerator refreshTokenIdGenerator;
        protected final TokenSaltGenerator tokenSaltGenerator;

        public AuthorizationByTokenUseCase authorizationByTokenUseCase() {
            return new AuthorizationByTokenUseCase(refreshTokenRepository, accessTokenStorage, userRepository);
        }

        public CreateAccessTokenUseCase createAccessTokenUseCase() {
            return new CreateAccessTokenUseCase(accessTokenIdGenerator, createRefreshTokenUseCase());
        }

        public CreateRefreshTokenUseCase createRefreshTokenUseCase() {
            return new CreateRefreshTokenUseCase(refreshTokenIdGenerator, refreshTokenRepository, tokenSaltGenerator);
        }

        public ExpireTokenUseCase expireTokenUseCase() {
            return new ExpireTokenUseCase(refreshTokenRepository);
        }

    }

    protected final RefreshTokenRepository refreshTokenRepository;
    protected final AccessTokenStorage accessTokenStorage;
    protected final UserRepository userRepository;
    protected final AccessTokenIdGenerator accessTokenIdGenerator;
    protected final RefreshTokenIdGenerator refreshTokenIdGenerator;
    protected final TokenSaltGenerator tokenSaltGenerator;
    protected final All all;

    public TokenConfig(RefreshTokenRepository refreshTokenRepository, AccessTokenStorage accessTokenStorage, UserRepository userRepository, AccessTokenIdGenerator accessTokenIdGenerator, RefreshTokenIdGenerator refreshTokenIdGenerator, TokenSaltGenerator tokenSaltGenerator) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.accessTokenStorage = accessTokenStorage;
        this.userRepository = userRepository;
        this.accessTokenIdGenerator = accessTokenIdGenerator;
        this.refreshTokenIdGenerator = refreshTokenIdGenerator;
        this.tokenSaltGenerator = tokenSaltGenerator;

        this.all = new All(refreshTokenRepository, accessTokenStorage, userRepository ,accessTokenIdGenerator, refreshTokenIdGenerator, tokenSaltGenerator);
    }
}
