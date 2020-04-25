package com.rcore.adapter.domain.user;

import com.rcore.adapter.domain.role.mapper.RoleMapper;
import com.rcore.adapter.domain.user.dto.UserDTO;
import com.rcore.adapter.domain.user.mapper.UserMapper;
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
public class UserAdminAdapter {

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

    public Boolean initAdminUser(String email, String password) throws AdminUserIsExistException {
        return userConfig.admin.InitAdminUseCase()
                .init(email, password);
    }

    public UserDTO updateUser(UserDTO userDTO) throws UserNotFoundException, UserAlreadyExistException, AuthorizationException, AuthenticationException {
        return userMapper.map(userConfig.admin.UpdateUserUseCase()
                .update(userMapper.inverseMap(userDTO)));
    }

    public Optional<UserDTO> findById(String id) throws AuthorizationException, AuthenticationException {
        return userConfig.admin.ViewUserUseCase()
                .findById(id)
                .map(userMapper::map);
    }

    public SearchResult<UserDTO> find(Long size, Long skip) throws AuthorizationException, AuthenticationException {
        SearchResult<UserEntity> result = userConfig.admin.ViewUserUseCase()
                .find(size, skip);

        return SearchResult.withItemsAndCount(
                userMapper.mapAll(result.getItems()),
                result.getCount()
        );
    }
}
