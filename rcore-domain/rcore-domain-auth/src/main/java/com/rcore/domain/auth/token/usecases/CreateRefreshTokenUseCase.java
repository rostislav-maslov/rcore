package com.rcore.domain.auth.token.usecases;

import com.rcore.commons.utils.DateTimeUtils;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.token.entity.RefreshTokenEntity;
import com.rcore.domain.auth.token.port.RefreshTokenIdGenerator;
import com.rcore.domain.auth.token.port.RefreshTokenRepository;
import com.rcore.domain.auth.token.port.TokenSaltGenerator;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class CreateRefreshTokenUseCase extends UseCase<CreateRefreshTokenUseCase.InputValues, SingletonEntityOutputValues<RefreshTokenEntity>> {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenIdGenerator refreshTokenIdGenerator;
    private final TokenSaltGenerator tokenSaltGenerator;

    @Override
    public SingletonEntityOutputValues<RefreshTokenEntity> execute(InputValues inputValues) {
        return SingletonEntityOutputValues.of(refreshTokenRepository.save(create(inputValues.getCredentialEntity().getId(), RefreshTokenEntity.CreateFrom.LOGIN, null)));
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        private final CredentialEntity credentialEntity;
    }

    private RefreshTokenEntity create(String credentialId, RefreshTokenEntity.CreateFrom createFrom, String refreshTokenId){
        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();

        refreshTokenEntity.setId(this.refreshTokenIdGenerator.generate());
        refreshTokenEntity.setCredentialId(credentialId);
        refreshTokenEntity.setExpireAt(DateTimeUtils.fromMillis(DateTimeUtils.getNowMillis() + refreshTokenEntity.getExpireTimeAccessToken()));
        refreshTokenEntity.setStatus(RefreshTokenEntity.Status.ACTIVE);

        refreshTokenEntity.setCreateFromTokenId(refreshTokenId);
        refreshTokenEntity.setCreateFromType(createFrom);

        refreshTokenEntity.setSalt(tokenSaltGenerator.generate());
        return refreshTokenEntity;
    }
}
