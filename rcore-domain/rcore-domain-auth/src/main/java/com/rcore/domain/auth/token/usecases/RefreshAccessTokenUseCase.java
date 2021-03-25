package com.rcore.domain.auth.token.usecases;

import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.exceptions.CredentialNotFoundException;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.auth.token.entity.AccessTokenEntity;
import com.rcore.domain.auth.token.entity.RefreshTokenEntity;
import com.rcore.domain.auth.token.entity.TokenPair;
import com.rcore.domain.auth.token.exception.RefreshTokenIsExpiredException;
import com.rcore.domain.auth.token.exception.RefreshTokenNotFoundException;
import com.rcore.domain.auth.token.port.RefreshTokenRepository;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.IdInputValues;
import com.rcore.domain.security.model.RefreshTokenData;
import com.rcore.domain.security.port.TokenConverter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class RefreshAccessTokenUseCase extends UseCase<RefreshAccessTokenUseCase.InputValues, RefreshAccessTokenUseCase.OutputValues> {

    private final TokenConverter<RefreshTokenData> tokenConverter;
    private final CredentialRepository credentialRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final ExpireRefreshTokenUseCase expireRefreshTokenUseCase;
    private final CreateAccessTokenUseCase createAccessTokenUseCase;

    @Override
    public OutputValues execute(InputValues inputValues) {

        RefreshTokenData refreshTokenData = tokenConverter.parse(inputValues.getRefreshToken());

        RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.findById(refreshTokenData.getId())
                .orElseThrow(() -> new RefreshTokenNotFoundException(refreshTokenData.getId()));

        CredentialEntity credentialEntity = credentialRepository.findById(refreshTokenEntity.getCredential().getId())
                .orElseThrow(() -> new CredentialNotFoundException(refreshTokenEntity.getCredential().getId()));

        if (!refreshTokenEntity.isActive()) {
            expireRefreshTokenUseCase.execute(IdInputValues.of(refreshTokenEntity.getId()));
            throw new RefreshTokenIsExpiredException(refreshTokenEntity.getId());
        }

        AccessTokenEntity accessTokenEntity = createAccessTokenUseCase.execute(CreateAccessTokenUseCase.InputValues.of(credentialEntity, refreshTokenEntity))
                .getEntity();

        return OutputValues.of(TokenPair.builder()
                .accessToken(accessTokenEntity)
                .refreshToken(refreshTokenEntity)
                .build());
    }

    @Value(staticConstructor = "of")
    public static class InputValues implements UseCase.InputValues {
        private final String refreshToken;
    }

    @Value(staticConstructor = "of")
    public static class OutputValues implements UseCase.OutputValues {
        private final TokenPair tokenPair;
    }
}
