package com.rcore.domain.auth.token.usecase;

import com.rcore.commons.utils.DateTimeUtils;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.token.entity.RefreshTokenEntity;
import com.rcore.domain.auth.token.port.RefreshTokenIdGenerator;
import com.rcore.domain.auth.token.port.RefreshTokenRepository;
import com.rcore.domain.auth.token.port.TokenSaltGenerator;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateRefreshTokenUseCase {
    private final RefreshTokenIdGenerator idGenerator;
    private final RefreshTokenRepository repository;
    private final TokenSaltGenerator tokenSaltGenerator;

    private RefreshTokenEntity create(String credentialId, RefreshTokenEntity.CreateFrom createFrom, String refreshTokenId){
        RefreshTokenEntity refreshTokenEntity = new RefreshTokenEntity();

        refreshTokenEntity.setId(this.idGenerator.generate());
        refreshTokenEntity.setCredentialId(credentialId);
        refreshTokenEntity.setExpireAt(DateTimeUtils.fromMillis(DateTimeUtils.getNowMillis() + refreshTokenEntity.getExpireTimeAccessToken()));
        refreshTokenEntity.setStatus(RefreshTokenEntity.Status.ACTIVE);

        refreshTokenEntity.setCreateFromTokenId(refreshTokenId);
        refreshTokenEntity.setCreateFromType(createFrom);

        refreshTokenEntity.setSalt(tokenSaltGenerator.generate());
        return refreshTokenEntity;
    }

    public RefreshTokenEntity create(CredentialEntity credentialEntity){
        return repository.save(create(credentialEntity.getId(), RefreshTokenEntity.CreateFrom.LOGIN, null));
    }

    public RefreshTokenEntity create(RefreshTokenEntity oldRefreshTokenEntity){
        return repository.save(create(oldRefreshTokenEntity.getCredentialId(), RefreshTokenEntity.CreateFrom.LOGIN, oldRefreshTokenEntity.getId()));
    }
}
