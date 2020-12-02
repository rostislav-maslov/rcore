package com.rcore.domain.auth.token.usecase;

import com.rcore.commons.utils.DateTimeUtils;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.token.entity.AccessTokenEntity;
import com.rcore.domain.auth.token.entity.RefreshTokenEntity;
import com.rcore.domain.auth.token.exception.RefreshTokenCreationException;
import com.rcore.domain.auth.token.port.AccessTokenIdGenerator;
import com.rcore.domain.auth.token.port.AccessTokenRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateAccessTokenUseCase {
    private final AccessTokenRepository accessTokenRepository;
    private final AccessTokenIdGenerator idGenerator;
    private final CreateRefreshTokenUseCase createRefreshTokenUseCase;

    public AccessTokenEntity create(CredentialEntity credentialEntity) throws RefreshTokenCreationException {
        RefreshTokenEntity refreshTokenEntity = createRefreshTokenUseCase.create(credentialEntity);

        return create(credentialEntity, refreshTokenEntity);
    }

    public AccessTokenEntity create(CredentialEntity credentialEntity, RefreshTokenEntity refreshTokenEntity) {
        AccessTokenEntity accessTokenEntity = new AccessTokenEntity();
        accessTokenEntity.setId(idGenerator.generate());
        accessTokenEntity.setCredentialId(refreshTokenEntity.getCredentialId());
        accessTokenEntity.setExpireAt(DateTimeUtils.fromMillis(DateTimeUtils.getNowMillis() + refreshTokenEntity.getExpireTimeAccessToken()));
        accessTokenEntity.setCreateFromRefreshTokenId(refreshTokenEntity.getId());

        accessTokenEntity.setSign(AccessTokenEntity.sign(accessTokenEntity.getId(), DateTimeUtils.getMillis(accessTokenEntity.getExpireAt()), refreshTokenEntity));

        accessTokenEntity = accessTokenRepository.save(accessTokenEntity);
        return accessTokenEntity;
    }
}
