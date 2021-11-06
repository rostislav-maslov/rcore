package com.rcore.domain.auth.authorization.usecases;

import com.rcore.domain.auth.token.entity.AccessTokenEntity;
import com.rcore.domain.auth.token.exception.AccessTokenNotFoundException;
import com.rcore.domain.auth.token.port.AccessTokenRepository;
import com.rcore.domain.auth.token.port.RefreshTokenRepository;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.security.model.AccessTokenData;
import com.rcore.domain.security.port.TokenGenerator;
import com.rcore.domain.security.port.TokenParser;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class LogoutUseCase extends UseCase<LogoutUseCase.InputValues, LogoutUseCase.OutputValues> {

    private final TokenParser<AccessTokenData> tokenGenerator;
    private final RefreshTokenRepository refreshTokenRepository;
    private final AccessTokenRepository accessTokenRepository;

    @Override
    public OutputValues execute(InputValues inputValues) {
        AccessTokenData accessToken = tokenGenerator.parseWithValidating(inputValues.getAccessToken());
        AccessTokenEntity accessTokenEntity = accessTokenRepository.findById(accessToken.getId())
                .orElseThrow(() -> new AccessTokenNotFoundException(accessToken.getId()));

        if (accessTokenEntity.getCreateByRefreshTokenId() != null) {
            refreshTokenRepository.findById(accessTokenEntity.getCreateByRefreshTokenId())
                    .ifPresent(token -> {
                        token.deactivate();
                        refreshTokenRepository.save(token);

                        accessTokenRepository.deactivateAllAccessTokenByRefreshTokenId(token.getId());
                    });
        } else {
            accessTokenEntity.deactivate();
            accessTokenRepository.save(accessTokenEntity);
        }

        return new OutputValues();
    }

    @Value(staticConstructor = "of")
    public static class InputValues implements UseCase.InputValues {
        private final String accessToken;
    }


    public static class OutputValues implements UseCase.OutputValues {

    }

}
