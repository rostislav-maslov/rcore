package com.rcore.domain.auth.token.usecases;

import com.rcore.commons.utils.DateTimeUtils;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.token.entity.AccessTokenEntity;
import com.rcore.domain.auth.token.entity.RefreshTokenEntity;
import com.rcore.domain.auth.token.port.AccessTokenIdGenerator;
import com.rcore.domain.auth.token.port.AccessTokenRepository;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class CreateAccessTokenUseCase extends UseCase<CreateAccessTokenUseCase.InputValues, SingletonEntityOutputValues<AccessTokenEntity>> {

    private final AccessTokenRepository accessTokenRepository;
    private final AccessTokenIdGenerator accessTokenIdGenerator;

    @Override
    public SingletonEntityOutputValues<AccessTokenEntity> execute(InputValues inputValues) {
        AccessTokenEntity accessTokenEntity = new AccessTokenEntity();
        accessTokenEntity.setId(accessTokenIdGenerator.generate());
        accessTokenEntity.setCredential(inputValues.getRefreshTokenEntity().getCredential());
        accessTokenEntity.setExpireAt(DateTimeUtils.fromMillis(DateTimeUtils.getNowMillis() + inputValues.getRefreshTokenEntity().getExpireTimeAccessToken()));
        accessTokenEntity.setCreateByRefreshTokenId(inputValues.getRefreshTokenEntity().getId());
        accessTokenEntity.setSign(AccessTokenEntity.sign(accessTokenEntity.getId(), DateTimeUtils.getMillis(accessTokenEntity.getExpireAt()), inputValues.getRefreshTokenEntity()));
        return SingletonEntityOutputValues.of(accessTokenRepository.save(accessTokenEntity));
    }

    @Value(staticConstructor = "of")
    public static class InputValues implements UseCase.InputValues {
        private final CredentialEntity credentialEntity;
        private final RefreshTokenEntity refreshTokenEntity;
    }
}
