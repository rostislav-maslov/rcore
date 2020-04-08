package com.rcore.domain.token.usecase;

import com.rcore.domain.role.entity.Role;
import com.rcore.domain.token.entity.AccessTokenEntity;
import com.rcore.domain.token.entity.RefreshTokenEntity;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.port.AccessTokenStorage;
import com.rcore.domain.token.port.AuthorizationPort;
import com.rcore.domain.token.port.RefreshTokenRepository;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.port.UserRepository;

import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AuthorizationByTokenUseCase implements AuthorizationPort {

    private final RefreshTokenRepository refreshTokenRepository;
    private final AccessTokenStorage accessTokenStorage;
    private final UserRepository userRepository;

    public AuthorizationByTokenUseCase(RefreshTokenRepository refreshTokenRepository, AccessTokenStorage accessTokenStorage, UserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.accessTokenStorage = accessTokenStorage;
        this.userRepository = userRepository;
    }

    @Override
    public Boolean checkAccess(AccessTokenEntity accessToken, Set<Role> userRoles) {
        if (new Date().getTime() > accessToken.getExpireAt().getTime()) return false;

        Optional<RefreshTokenEntity> refreshTokenEntity = refreshTokenRepository.findById(accessToken.getCreateFromRefreshTokenId());
        if (!refreshTokenEntity.isPresent()) return false;

        if (refreshTokenEntity.get().isActive() == false) return false;

        if (AccessTokenEntity.sign(accessToken.getId(), accessToken.getExpireAt().getTime(), refreshTokenEntity.get()).equals(accessToken.getSign()) == false)
            return false;

        return true;
    }

    public void putInStorage(AccessTokenEntity accessTokenEntity) {
        accessTokenStorage.put(accessTokenEntity);
    }

    public AccessTokenEntity currentAccessToken() throws AuthenticationException {
        AccessTokenEntity accessTokenEntity = accessTokenStorage.current().orElseThrow(() -> new AuthenticationException());
        if (checkAccess(accessTokenEntity, new HashSet<>()) == false) {
            throw new AuthenticationException();
        }

        return accessTokenEntity;
    }

    public UserEntity currentUser() throws AuthenticationException {
        AccessTokenEntity accessTokenEntity = currentAccessToken();

        Optional<UserEntity> userEntity = userRepository.findById(accessTokenEntity.getUserId());
        if(userEntity.isPresent() == false) throw new AuthenticationException();

        return userEntity.get();
    }

}
