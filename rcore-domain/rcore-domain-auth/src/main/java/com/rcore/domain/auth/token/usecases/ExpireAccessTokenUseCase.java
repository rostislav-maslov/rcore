package com.rcore.domain.auth.token.usecases;

import com.rcore.domain.auth.token.entity.AccessTokenEntity;
import com.rcore.domain.auth.token.exception.AccessTokenNotFoundException;
import com.rcore.domain.auth.token.port.AccessTokenRepository;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.IdInputValues;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class ExpireAccessTokenUseCase extends UseCase<IdInputValues<String>, SingletonEntityOutputValues<AccessTokenEntity>> {

    private final AccessTokenRepository accessTokenRepository;

    @Override
    public SingletonEntityOutputValues<AccessTokenEntity> execute(IdInputValues<String> inputValues) {

        AccessTokenEntity accessTokenEntity = accessTokenRepository.findById(inputValues.getId())
                .orElseThrow(() -> new AccessTokenNotFoundException(inputValues.getId()));

        accessTokenEntity.expire();

        return SingletonEntityOutputValues.of(accessTokenRepository.save(accessTokenEntity));
    }

}
