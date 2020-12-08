package com.rcore.domain.auth.token.usecases;

import com.rcore.domain.auth.token.entity.AccessTokenEntity;
import com.rcore.domain.auth.token.exception.AccessTokenNotFoundException;
import com.rcore.domain.auth.token.port.AccessTokenRepository;
import com.rcore.domain.commons.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class ExpireAccessTokenUseCase extends UseCase<ExpireAccessTokenUseCase.InputValues, ExpireAccessTokenUseCase.OutputValues> {

    private final AccessTokenRepository accessTokenRepository;

    @Override
    public OutputValues execute(InputValues inputValues) {

        AccessTokenEntity accessTokenEntity = accessTokenRepository.findById(inputValues.getId())
                .orElseThrow(() -> new AccessTokenNotFoundException(inputValues.getId()));

        accessTokenEntity.expire();

        return new OutputValues(accessTokenRepository.save(accessTokenEntity));
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        private final String id;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final AccessTokenEntity accessTokenEntity;
    }

}
