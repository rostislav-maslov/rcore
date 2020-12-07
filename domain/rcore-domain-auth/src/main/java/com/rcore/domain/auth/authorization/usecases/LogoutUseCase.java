package com.rcore.domain.auth.authorization.usecases;

import com.rcore.domain.auth.token.entity.RefreshTokenEntity;
import com.rcore.domain.auth.token.exception.RefreshTokenNotFoundException;
import com.rcore.domain.auth.token.port.AccessTokenRepository;
import com.rcore.domain.auth.token.port.RefreshTokenRepository;
import com.rcore.domain.auth.token.port.SessionTokenRepository;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.security.model.RefreshTokenData;
import com.rcore.domain.security.port.TokenConverter;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.util.Optional;

@RequiredArgsConstructor
public class LogoutUseCase extends UseCase<LogoutUseCase.InputValues, LogoutUseCase.OutputValues> {

    private final SessionTokenRepository sessionTokenRepository;
    private final TokenConverter<RefreshTokenData> tokenConverter;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AccessTokenRepository accessTokenRepository;

    @Override
    public OutputValues execute(InputValues inputValues) {
        Optional<String> sessionToken = sessionTokenRepository.getSessionAccessToken();
        if (sessionToken.isEmpty())
            return new OutputValues();

        RefreshTokenData refreshTokenData = tokenConverter.parse(sessionToken.get());
        RefreshTokenEntity refreshTokenEntity = refreshTokenRepository.findById(refreshTokenData.getId())
                .orElseThrow(() -> new RefreshTokenNotFoundException(refreshTokenData.getId()));

        refreshTokenEntity.deactivate();
        refreshTokenRepository.save(refreshTokenEntity);

        //Деактивируем все accessToken выданные данным refreshToken
        accessTokenRepository.deactivateAllAccessTokenByRefreshTokenId(refreshTokenEntity.getId());

        return new OutputValues();
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
    }


    public static class OutputValues implements UseCase.OutputValues {

    }

}
