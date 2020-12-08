package com.rcore.domain.auth.token.usecases;

import com.rcore.domain.auth.token.entity.RefreshTokenEntity;
import com.rcore.domain.auth.token.port.AccessTokenRepository;
import com.rcore.domain.commons.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class ExpireAccessTokensByRefreshTokenUseCase extends UseCase<ExpireAccessTokensByRefreshTokenUseCase.InputValues, ExpireAccessTokensByRefreshTokenUseCase.OutputValues> {

    private final AccessTokenRepository accessTokenRepository;

    @Override
    public OutputValues execute(InputValues inputValues) {
        accessTokenRepository.expireAllAccessTokenByRefreshTokenId(inputValues.getRefreshTokenEntity().getId());
        return new OutputValues();
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        private final RefreshTokenEntity refreshTokenEntity;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {

    }

}
