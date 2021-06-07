package com.rcore.domain.auth.token.config;

import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.auth.token.port.*;
import com.rcore.domain.auth.token.usecases.*;
import com.rcore.domain.security.model.AccessTokenData;
import com.rcore.domain.security.model.RefreshTokenData;
import com.rcore.domain.security.port.TokenGenerator;
import com.rcore.domain.security.port.TokenParser;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

@Accessors(fluent = true)
@Getter
public class TokenConfig {

    private final CreateAccessTokenUseCase createAccessToken;
    private final CreateRefreshTokenUseCase createRefreshToken;
    private final DeactivateAccessTokensByRefreshToken deactivateAccessTokensByRefreshToken;
    private final DecodeAccessTokenUseCase decodeAccessToken;
    private final DecodeRefreshTokenUseCase decodeRefreshToken;
    private final EncodeAccessTokenUseCase encodeAccessToken;
    private final EncodeRefreshTokenUseCase encodeRefreshToken;
    private final ExpireAccessTokensByRefreshTokenUseCase expireAccessTokensByRefreshToken;
    private final ExpireAccessTokenUseCase expireAccessToken;
    private final ExpireRefreshTokenUseCase expireRefreshToken;
    private final FindAccessTokensUseCase findAccessTokens;
    private final FindRefreshTokensUseCase findRefreshTokens;
    private final FindRefreshTokenByIdUseCase findRefreshTokenById;
    private final FindAccessTokenByIdUseCase findAccessTokenById;
    private final RefreshAccessTokenUseCase refreshAccessToken;

    public TokenConfig(
            AccessTokenRepository accessTokenRepository,
            AccessTokenIdGenerator<?> accessTokenIdGenerator,
            RefreshTokenRepository refreshTokenRepository,
            RefreshTokenIdGenerator<?> refreshTokenIdGenerator,
            TokenSaltGenerator tokenSaltGenerator,
            TokenGenerator<AccessTokenData> accessTokenDataTokenGenerator,
            TokenParser<AccessTokenData> accessTokenDataTokenParser,
            TokenGenerator<RefreshTokenData> refreshTokenDataTokenGenerator,
            TokenParser<RefreshTokenData> refreshTokenDataTokenParser,
            CredentialRepository credentialRepository,
            TokenLifeCycleConfig tokenLifeCycleConfig
    ) {
        this.findAccessTokenById = new FindAccessTokenByIdUseCase(accessTokenRepository);
        this.findRefreshTokenById = new FindRefreshTokenByIdUseCase(refreshTokenRepository);
        this.findRefreshTokens = new FindRefreshTokensUseCase(refreshTokenRepository);
        this.findAccessTokens = new FindAccessTokensUseCase(accessTokenRepository);
        this.expireAccessToken = new ExpireAccessTokenUseCase(accessTokenRepository);
        this.encodeRefreshToken = new EncodeRefreshTokenUseCase(findRefreshTokenById, refreshTokenDataTokenGenerator);
        this.encodeAccessToken = new EncodeAccessTokenUseCase(findAccessTokenById, accessTokenDataTokenGenerator);
        this.decodeRefreshToken = new DecodeRefreshTokenUseCase(findRefreshTokenById, refreshTokenDataTokenParser);
        this.decodeAccessToken = new DecodeAccessTokenUseCase(findAccessTokenById, accessTokenDataTokenParser);
        this.createAccessToken = new CreateAccessTokenUseCase(accessTokenRepository, accessTokenIdGenerator, tokenLifeCycleConfig);
        this.createRefreshToken = new CreateRefreshTokenUseCase(refreshTokenRepository, refreshTokenIdGenerator, tokenSaltGenerator, tokenLifeCycleConfig);
        this.deactivateAccessTokensByRefreshToken = new DeactivateAccessTokensByRefreshToken(accessTokenRepository);
        this.expireAccessTokensByRefreshToken = new ExpireAccessTokensByRefreshTokenUseCase(accessTokenRepository);
        this.expireRefreshToken = new ExpireRefreshTokenUseCase(refreshTokenRepository, deactivateAccessTokensByRefreshToken);
        this.refreshAccessToken = new RefreshAccessTokenUseCase(refreshTokenDataTokenParser, credentialRepository, refreshTokenRepository, expireRefreshToken, createAccessToken);
    }

}
