package com.rcore.adapter.domain.user;

import com.rcore.adapter.domain.role.mapper.RoleMapper;
import com.rcore.adapter.domain.user.dto.UserDTO;
import com.rcore.adapter.domain.user.mapper.UserMapper;
import com.rcore.domain.base.port.SearchRequest;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.config.UserConfig;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.AdminUserIsExistException;
import com.rcore.domain.user.exception.UserAlreadyExistException;
import com.rcore.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserSecureAdapter {

    private UserMapper userMapper = new UserMapper(new RoleMapper());
    private final UserConfig userConfig;

    public UserDTO activateUser(UserDTO userDTO) throws AuthorizationException, AuthenticationException {
        return userMapper.map(userConfig.admin.activateUseCase()
                .activate(userMapper.inverseMap(userDTO)));
    }

    public UserDTO blockUser(UserDTO userDTO) throws AuthorizationException, AuthenticationException {
        return userMapper.map(userConfig.admin.BlockUseCase()
                .block(userMapper.inverseMap(userDTO)));
    }

    public UserDTO createUserByEmail(String email, String password) throws UserAlreadyExistException, AuthorizationException, AuthenticationException {
        return userMapper.map(userConfig.admin.CreateUseCase()
                .createByEmail(email, password));
    }

    public Boolean deleteUser(UserDTO userDTO) throws UserAlreadyExistException, AuthorizationException, AuthenticationException {
        return userConfig.admin.DeleteUserUseCase()
                .delete(userMapper.inverseMap(userDTO));
    }

    public UserDTO updateUser(UserDTO userDTO) throws UserNotFoundException, UserAlreadyExistException, AuthorizationException, AuthenticationException {
        return userMapper.map(userConfig.admin.UpdateUserUseCase()
                .update(userMapper.inverseMap(userDTO)));
    }

}
