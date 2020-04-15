package com.rcore.adapter.domain.user;

import com.rcore.adapter.domain.user.mapper.UserMapper;
import com.rcore.adapter.domain.user.dto.UserDTO;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.config.UserConfig;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.UserAlreadyExistException;
import com.rcore.domain.user.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class UserAdminAdapter {

    private UserMapper userMapper = new UserMapper();
    private final UserConfig userConfig;

    public UserDTO activateUser(UserDTO actor, UserDTO userDTO) throws AuthorizationException {
        return userMapper.map(userConfig.admin.activateUseCase(userMapper.inverseMap(actor))
                .activate(userMapper.inverseMap(userDTO)));
    }

    public UserDTO blockUser(UserDTO actor, UserDTO userDTO) throws AuthorizationException {
        return userMapper.map(userConfig.admin.BlockUseCase(userMapper.inverseMap(actor))
                .block(userMapper.inverseMap(userDTO)));
    }

    public UserDTO createUserByEmail(UserDTO actor, String email, String password) throws UserAlreadyExistException, AuthorizationException {
        return userMapper.map(userConfig.admin.CreateUseCase(userMapper.inverseMap(actor))
                .createByEmail(email, password));
    }

    public Boolean deleteUser(UserDTO actor, UserDTO userDTO) throws UserAlreadyExistException, AuthorizationException {
        return userConfig.admin.DeleteUserUseCase(userMapper.inverseMap(actor))
                .delete(userMapper.inverseMap(userDTO));
    }

    public Boolean initAdminUser(String email, String password) throws AuthorizationException {
        return userConfig.admin.InitAdminUseCase()
                .init(email, password);
    }

    public UserDTO updateUser(UserDTO actor, UserDTO userDTO) throws UserNotFoundException, UserAlreadyExistException, AuthorizationException {
        return userMapper.map(userConfig.admin.UpdateUserUseCase(userMapper.inverseMap(actor))
                .update(userMapper.inverseMap(userDTO)));
    }

    public Optional<UserDTO> findById(UserDTO actor, String id) throws AuthorizationException {
        return userConfig.admin.ViewUserUseCase(userMapper.inverseMap(actor))
                .findById(id).map(userMapper::map);
    }

    public SearchResult<UserDTO> find(UserDTO actor, Long size, Long skip) throws AuthorizationException {
        SearchResult<UserEntity> result = userConfig.admin.ViewUserUseCase(userMapper.inverseMap(actor))
                .find(size, skip);

        return SearchResult.withItemsAndCount(
                userMapper.mapAll(result.getItems()),
                result.getCount()
        );
    }
}
