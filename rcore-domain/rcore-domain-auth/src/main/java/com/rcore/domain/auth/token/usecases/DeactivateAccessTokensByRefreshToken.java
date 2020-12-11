package com.rcore.domain.auth.token.usecases;

import com.rcore.domain.auth.token.entity.RefreshTokenEntity;
import com.rcore.domain.auth.token.port.AccessTokenRepository;
import com.rcore.domain.commons.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class DeactivateAccessTokensByRefreshToken extends UseCase<DeactivateAccessTokensByRefreshToken.InputValues, DeactivateAccessTokensByRefreshToken.OutputValues> {

    private final AccessTokenRepository accessTokenRepository;

    @Override
    public OutputValues execute(InputValues inputValues) {
        accessTokenRepository.deactivateAllAccessTokenByRefreshTokenId(inputValues.getRefreshTokenEntity().getId());
        return new OutputValues();
    }

    @Value(staticConstructor = "of")
    public static class InputValues implements UseCase.InputValues {
        private final RefreshTokenEntity refreshTokenEntity;
    }

    public static class OutputValues implements UseCase.OutputValues {

    }
}
