package com.rcore.domain.auth.token.config;

import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.auth.token.port.*;
import com.rcore.domain.auth.token.usecases.*;
import com.rcore.domain.security.model.RefreshTokenData;
import com.rcore.domain.security.port.TokenConverter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TokenConfig {

    private final AccessTokenRepository accessTokenRepository;
    private final AccessTokenIdGenerator accessTokenIdGenerator;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenIdGenerator refreshTokenIdGenerator;
    private final TokenSaltGenerator tokenSaltGenerator;
    private final TokenConverter<RefreshTokenData> tokenConverter;
    private final CredentialRepository credentialRepository;

    public CreateAccessTokenUseCase createAccessTokenUseCase() {
        return new CreateAccessTokenUseCase(accessTokenRepository, accessTokenIdGenerator);
    }

    public CreateRefreshTokenUseCase createRefreshTokenUseCase() {
        return new CreateRefreshTokenUseCase(refreshTokenRepository, refreshTokenIdGenerator, tokenSaltGenerator);
    }

    public DeactivateAccessTokensByRefreshToken deactivateAccessTokensByRefreshToken() {
        return new DeactivateAccessTokensByRefreshToken(accessTokenRepository);
    }

    public ExpireAccessTokensByRefreshTokenUseCase expireAccessTokensByRefreshTokenUseCase() {
        return new ExpireAccessTokensByRefreshTokenUseCase(accessTokenRepository);
    }

    public ExpireRefreshTokenUseCase expireRefreshTokenUseCase() {
        return new ExpireRefreshTokenUseCase(refreshTokenRepository, deactivateAccessTokensByRefreshToken());
    }

    public RefreshAccessTokenUseCase refreshAccessTokenUseCase() {
        return new RefreshAccessTokenUseCase(tokenConverter, credentialRepository, refreshTokenRepository, expireRefreshTokenUseCase(), createAccessTokenUseCase());
    }

}
