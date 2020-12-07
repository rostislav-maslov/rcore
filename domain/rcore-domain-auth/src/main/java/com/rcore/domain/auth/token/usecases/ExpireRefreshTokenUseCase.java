package com.rcore.domain.auth.token.usecases;

import com.rcore.domain.auth.token.entity.RefreshTokenEntity;
import com.rcore.domain.auth.token.exception.RefreshTokenNotFoundException;
import com.rcore.domain.auth.token.port.RefreshTokenRepository;
import com.rcore.domain.commons.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class ExpireRefreshTokenUseCase extends UseCase<ExpireRefreshTokenUseCase.InputValues, ExpireRefreshTokenUseCase.OutputValues> {

    private final RefreshTokenRepository refreshTokenRepository;
    private final ExpireAccessTokensByRefreshTokenUseCase expireAccessTokensByRefreshTokenUseCase;

    @Override
    public OutputValues execute(InputValues inputValues) {

        RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.findById(inputValues.getId())
                .orElseThrow(() -> new RefreshTokenNotFoundException(inputValues.getId()));

        refreshTokenEntity.expire();

        expireAccessTokensByRefreshTokenUseCase.execute(new ExpireAccessTokensByRefreshTokenUseCase.InputValues(refreshTokenEntity));

        return new OutputValues(refreshTokenRepository.save(refreshTokenEntity));
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        private final String id;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final RefreshTokenEntity refreshTokenEntity;
    }

}
