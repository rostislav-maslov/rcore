package com.rcore.adapter.domain.user;

import com.rcore.adapter.domain.role.dto.RoleDTO;
import com.rcore.adapter.domain.role.mapper.RoleMapper;
import com.rcore.adapter.domain.token.dto.RefreshTokenDTO;
import com.rcore.adapter.domain.token.mapper.AccessTokenMapper;
import com.rcore.adapter.domain.token.mapper.RefreshTokenMapper;
import com.rcore.adapter.domain.user.dto.TokenPairDTO;
import com.rcore.adapter.domain.user.dto.UserDTO;
import com.rcore.adapter.domain.user.mapper.TokenPairMapper;
import com.rcore.adapter.domain.user.mapper.UserMapper;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.RefreshTokenIsExpiredException;
import com.rcore.domain.user.config.UserConfig;
import com.rcore.domain.user.exception.AdminUserIsExistException;
import com.rcore.domain.user.exception.UserBlockedException;
import com.rcore.domain.user.exception.UserWithPhoneAlreadyExistForUpdateException;
import com.rcore.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserAllAdapter {
    private final UserConfig userConfig;
    private RoleMapper roleMapper = new RoleMapper();
    private UserMapper userMapper = new UserMapper(new RoleMapper());
    private AccessTokenMapper accessTokenMapper = new AccessTokenMapper();
    private RefreshTokenMapper refreshTokenMapper = new RefreshTokenMapper();
    private TokenPairMapper tokenPairMapper = new TokenPairMapper(accessTokenMapper, refreshTokenMapper);

    public Optional<UserDTO> findById(String id) {
        return userConfig.all.viewUserUserCase()
                .findById(id)
                .map(userMapper::map);
    }

    public Optional<UserDTO> findByPhone(Long phone) {
        return userConfig.all.viewUserUserCase()
                .findByPhone(phone)
                .map(userMapper::map);
    }

    public Optional<UserDTO> findByEmail(String email) {
        return userConfig.all.viewUserUserCase()
                .findByEmail(email)
                .map(userMapper::map);
    }

    public TokenPairDTO authentication(String email, String password) throws UserNotFoundException, UserBlockedException, AuthenticationException {
        return tokenPairMapper.map(userConfig.all.emailAuthenticationUseCase()
                .authentication(email, password));
    }

    public TokenPairDTO getNewTokenPairByRefreshToken(RefreshTokenDTO refreshToken) throws UserNotFoundException, UserBlockedException, AuthenticationException, RefreshTokenIsExpiredException {
        return tokenPairMapper.map(userConfig.all.emailAuthenticationUseCase()
                .getNewTokenPairByRefreshToken(refreshTokenMapper.inverseMap(refreshToken)));
    }

    public Boolean initAdminUser(String email, String password) throws AdminUserIsExistException {
        return userConfig.admin.InitAdminUseCase()
                .init(email, password);
    }

    public UserDTO create(Long phone, RoleDTO role) throws UserWithPhoneAlreadyExistForUpdateException {
        return userMapper.map(userConfig.all.createUserByPhoneNumber()
                .create(phone, roleMapper.inverseMap(role)));
    }
}
