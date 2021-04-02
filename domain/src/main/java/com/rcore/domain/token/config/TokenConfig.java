package com.rcore.domain.token.config;

import com.rcore.domain.token.port.*;
import com.rcore.domain.token.usecase.*;
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
        protected final RefreshTokenStorage refreshTokenStorage;
        protected final UserRepository userRepository;
        protected final AccessTokenIdGenerator accessTokenIdGenerator;
        protected final RefreshTokenIdGenerator refreshTokenIdGenerator;
        protected final TokenSaltGenerator tokenSaltGenerator;

        public AuthorizationByTokenUseCase authorizationByTokenUseCase() {
            return new AuthorizationByTokenUseCase(accessTokenStorage, refreshTokenStorage, userRepository);
        }

        public CreateAccessTokenUseCase createAccessTokenUseCase() {
            return new CreateAccessTokenUseCase(accessTokenIdGenerator, accessTokenStorage, createRefreshTokenUseCase());
        }

        public CreateRefreshTokenUseCase createRefreshTokenUseCase() {
            return new CreateRefreshTokenUseCase(refreshTokenIdGenerator, refreshTokenStorage, tokenSaltGenerator);
        }

        public ExpireTokenUseCase expireTokenUseCase() {
            return new ExpireTokenUseCase(userRepository, refreshTokenStorage, accessTokenStorage);
        }

        public ViewAccessTokenUseCase viewAccessTokenUseCase() {
            return new ViewAccessTokenUseCase(userRepository, accessTokenStorage);
        }

        public ViewRefreshTokenUseCase viewRefreshTokenUseCase() {
            return new ViewRefreshTokenUseCase(refreshTokenStorage);
        }
    }

    protected final All all;

    public TokenConfig(RefreshTokenRepository refreshTokenRepository, AccessTokenStorage accessTokenStorage, RefreshTokenStorage refreshTokenStorage, UserRepository userRepository, AccessTokenIdGenerator accessTokenIdGenerator, RefreshTokenIdGenerator refreshTokenIdGenerator, TokenSaltGenerator tokenSaltGenerator) {


        this.all = new All(refreshTokenRepository, accessTokenStorage, refreshTokenStorage, userRepository, accessTokenIdGenerator, refreshTokenIdGenerator, tokenSaltGenerator);
    }
}
