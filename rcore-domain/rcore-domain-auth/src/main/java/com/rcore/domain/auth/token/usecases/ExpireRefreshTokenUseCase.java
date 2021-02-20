package com.rcore.domain.auth.token.usecases;

import com.rcore.domain.auth.token.entity.RefreshTokenEntity;
import com.rcore.domain.auth.token.exception.RefreshTokenNotFoundException;
import com.rcore.domain.auth.token.port.RefreshTokenRepository;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.IdInputValues;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class ExpireRefreshTokenUseCase extends UseCase<IdInputValues<String>, SingletonEntityOutputValues<RefreshTokenEntity>> {

    private final RefreshTokenRepository refreshTokenRepository;
    private final DeactivateAccessTokensByRefreshToken deactivateAccessTokensByRefreshToken;

    @Override
    public SingletonEntityOutputValues<RefreshTokenEntity> execute(IdInputValues<String> inputValues) {

        RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.findById(inputValues.getId())
                .orElseThrow(() -> new RefreshTokenNotFoundException(inputValues.getId()));

        refreshTokenEntity.expire();
        deactivateAccessTokensByRefreshToken.execute(DeactivateAccessTokensByRefreshToken.InputValues.of(refreshTokenEntity));

        return SingletonEntityOutputValues.of(refreshTokenRepository.save(refreshTokenEntity));
    }

}
