package com.rcore.domain.auth.token.usecases;

import com.rcore.commons.utils.DateTimeUtils;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.token.entity.RefreshTokenEntity;
import com.rcore.domain.auth.token.port.RefreshTokenIdGenerator;
import com.rcore.domain.auth.token.port.RefreshTokenRepository;
import com.rcore.domain.auth.token.port.TokenSaltGenerator;
import com.rcore.domain.commons.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class CreateRefreshTokenUseCase extends UseCase<CreateRefreshTokenUseCase.InputValues, CreateRefreshTokenUseCase.OutputValues> {

    private final RefreshTokenRepository refreshTokenRepository;
    private final RefreshTokenIdGenerator refreshTokenIdGenerator;
    private final TokenSaltGenerator tokenSaltGenerator;

    @Override
    public OutputValues execute(InputValues inputValues) {
        return new OutputValues(refreshTokenRepository.save(create(inputValues.getCredentialEntity().getId(), RefreshTokenEntity.CreateFrom.LOGIN, null)));
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        private final CredentialEntity credentialEntity;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final RefreshTokenEntity refreshTokenEntity;
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
