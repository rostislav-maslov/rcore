package com.rcore.domain.token.usecase;

import com.rcore.domain.role.entity.Role;
import com.rcore.domain.token.entity.AccessTokenEntity;
import com.rcore.domain.token.entity.RefreshTokenEntity;
import com.rcore.domain.token.port.AuthorizationPort;
import com.rcore.domain.token.port.RefreshTokenRepository;

import java.util.Date;
import java.util.Optional;
import java.util.Set;

public class AuthorizationByTokenUseCase implements AuthorizationPort {

    private final RefreshTokenRepository refreshTokenRepository;

    public AuthorizationByTokenUseCase(RefreshTokenRepository refreshTokenRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
    }

    @Override
    public Boolean checkAccess(AccessTokenEntity accessToken, Set<Role> userRoles) {
        if(new Date().getTime() > accessToken.getExpireAt().getTime()) return false;

        Optional<RefreshTokenEntity> refreshTokenEntity = refreshTokenRepository.findById(accessToken.getCreateFromRefreshTokenId());
        if(!refreshTokenEntity.isPresent()) return false;

        if(refreshTokenEntity.get().isActive() == false) return false;

        if(AccessTokenEntity.sign(accessToken.getId(), accessToken.getExpireAt().getTime(), refreshTokenEntity.get()).equals(accessToken.getSign()) == false) return false;

        return true;
    }

}
