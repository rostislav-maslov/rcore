package com.rcore.adapter.domain.user;

import com.rcore.adapter.domain.role.mapper.RoleMapper;
import com.rcore.adapter.domain.token.dto.RefreshTokenDTO;
import com.rcore.adapter.domain.token.mapper.AccessTokenMapper;
import com.rcore.adapter.domain.token.mapper.RefreshTokenMapper;
import com.rcore.adapter.domain.user.dto.TokenPairDTO;
import com.rcore.adapter.domain.user.dto.UserDTO;
import com.rcore.adapter.domain.user.mapper.TokenPairMapper;
import com.rcore.adapter.domain.user.mapper.UserMapper;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.user.config.UserConfig;
import com.rcore.domain.user.exception.UserBlockedException;
import com.rcore.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserAllAdapter {
    private final UserConfig userConfig;

    private UserMapper userMapper = new UserMapper(new RoleMapper());
    private AccessTokenMapper accessTokenMapper = new AccessTokenMapper();
    private RefreshTokenMapper refreshTokenMapper = new RefreshTokenMapper();
    private TokenPairMapper tokenPairMapper = new TokenPairMapper(accessTokenMapper,refreshTokenMapper);

    public Optional<UserDTO> findById(String id) {
        return userConfig.all.viewUserUserCase()
                .findById(id)
                .map(userMapper::map);
    }

    public TokenPairDTO authentication(String email, String password) throws UserNotFoundException, UserBlockedException, AuthenticationException {
        return tokenPairMapper.map(userConfig.all.emailAuthenticationUseCase()
                .authentication(email, password));
    }

    public TokenPairDTO getNewTokenPairByRefreshToken(RefreshTokenDTO refreshToken) throws UserNotFoundException, UserBlockedException, AuthenticationException {
        return tokenPairMapper.map(userConfig.all.emailAuthenticationUseCase()
                .getNewTokenPairByRefreshToken(refreshTokenMapper.inverseMap(refreshToken)));
    }
}
