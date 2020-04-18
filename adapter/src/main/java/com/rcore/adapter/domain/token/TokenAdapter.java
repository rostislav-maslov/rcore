package com.rcore.adapter.domain.token;

import com.rcore.adapter.domain.token.dto.AccessTokenDTO;
import com.rcore.adapter.domain.token.dto.RefreshTokenDTO;
import com.rcore.adapter.domain.token.mapper.AccessTokenMapper;
import com.rcore.adapter.domain.token.mapper.RefreshTokenMapper;
import com.rcore.adapter.domain.user.dto.UserDTO;
import com.rcore.adapter.domain.user.mapper.UserMapper;
import com.rcore.domain.role.entity.Role;
import com.rcore.domain.token.config.TokenConfig;
import com.rcore.domain.token.entity.RefreshTokenEntity;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.RefreshTokenCreationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.exception.TokenExpiredException;
import com.rcore.domain.user.exception.UserBlockedException;
import com.rcore.domain.user.exception.UserNotExistException;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@RequiredArgsConstructor
public class TokenAdapter {
    private AccessTokenMapper accessTokenMapper = new AccessTokenMapper();
    private RefreshTokenMapper refreshTokenMapper = new RefreshTokenMapper();
    private UserMapper userMapper = new UserMapper();
    private final TokenConfig tokenConfig;

    public Boolean checkAccess(AccessTokenDTO accessToken, Set<Role> roles) {
        return tokenConfig.getAll().authorizationByTokenUseCase().checkAccess(accessTokenMapper.inverseMap(accessToken), roles);
    }

    public void putInStorage(AccessTokenDTO accessToken) {
        tokenConfig.getAll().authorizationByTokenUseCase().putInStorage(accessTokenMapper.inverseMap(accessToken));
    }

    public AccessTokenDTO currentAccessToken() throws AuthenticationException {
        return accessTokenMapper.map(tokenConfig.getAll().authorizationByTokenUseCase().currentAccessToken());
    }

    public UserDTO getUserByAccessToken(AccessTokenDTO accessToken) throws UserNotExistException, UserBlockedException, TokenExpiredException {
        return userMapper.map(tokenConfig.getAll().authorizationByTokenUseCase()
                .getUserByAccessToken(accessTokenMapper.inverseMap(accessToken)));
    }

    public UserDTO currentUser() throws AuthenticationException {
        return userMapper.map(tokenConfig.getAll().authorizationByTokenUseCase().currentUser());
    }

    public AccessTokenDTO createAccessToken(UserDTO user) throws RefreshTokenCreationException {
        return accessTokenMapper.map(tokenConfig.getAll().createAccessTokenUseCase()
                .create(userMapper.inverseMap(user)));
    }

    public AccessTokenDTO createAccessToken(UserDTO user, RefreshTokenDTO refreshToken) {
        return accessTokenMapper.map(tokenConfig.getAll().createAccessTokenUseCase()
                .create(userMapper.inverseMap(user), refreshTokenMapper.inverseMap(refreshToken)));
    }

    public RefreshTokenDTO createRefreshToken(UserDTO user) {
        return refreshTokenMapper.map(tokenConfig.getAll().createRefreshTokenUseCase()
                .create(userMapper.inverseMap(user)));
    }

    public RefreshTokenDTO createRefreshToken(RefreshTokenDTO oldRefreshToken) {
        return refreshTokenMapper.map(tokenConfig.getAll().createRefreshTokenUseCase()
                .create(refreshTokenMapper.inverseMap(oldRefreshToken)));
    }

    public void logout(UserDTO user) {
        tokenConfig.getAll().expireTokenUseCase()
                .logout(userMapper.inverseMap(user));
    }

    public void logout(RefreshTokenDTO refreshToken) {
        tokenConfig.getAll().expireTokenUseCase()
                .logout(refreshTokenMapper.inverseMap(refreshToken));
    }

    public void logout(AccessTokenDTO accessToken) {
        tokenConfig.getAll().expireTokenUseCase()
                .logout(accessTokenMapper.inverseMap(accessToken));
    }


}
