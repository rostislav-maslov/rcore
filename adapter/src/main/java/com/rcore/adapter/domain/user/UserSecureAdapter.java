package com.rcore.adapter.domain.user;

import com.rcore.adapter.domain.role.mapper.RoleMapper;
import com.rcore.adapter.domain.user.dto.UserDTO;
import com.rcore.adapter.domain.user.mapper.UserMapper;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.config.UserConfig;
import com.rcore.domain.user.exception.*;
import com.rcore.domain.user.usecase.admin.commands.ChangeUserPasswordCommand;
import com.rcore.domain.user.usecase.admin.commands.CreateUserCommand;
import com.rcore.domain.user.usecase.admin.commands.UpdateUserCommand;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserSecureAdapter {

    private UserMapper userMapper = new UserMapper(new RoleMapper());
    private final UserConfig userConfig;

    public UserDTO activateUser(UserDTO userDTO) throws AuthorizationException, AuthenticationException, TokenExpiredException {
        return userMapper.map(userConfig.admin.activateUseCase()
                .activate(userMapper.inverseMap(userDTO)));
    }

    public UserDTO blockUser(UserDTO userDTO) throws AuthorizationException, AuthenticationException, TokenExpiredException {
        return userMapper.map(userConfig.admin.BlockUseCase()
                .block(userMapper.inverseMap(userDTO)));
    }

    public UserDTO create(CreateUserCommand createUserCommand) throws AuthorizationException, RoleIsRequiredException, PhoneIsRequiredException, TokenExpiredException, EmailAndPasswordIsRequiredException, UserWithPhoneAlreadyExistException, UserAlreadyExistException, AuthenticationException {
        return userMapper.map(userConfig.admin.CreateUseCase()
                .create(createUserCommand));
    }

//    public UserDTO createUserByEmail(String email, String password, List<String> roleIds) throws UserAlreadyExistException, AuthorizationException, AuthenticationException, TokenExpiredException {
//        return userMapper.map(userConfig.admin.CreateUseCase()
//                .createByEmail(email, password, roleIds));
//    }

    public Boolean deleteUser(String id) throws UserAlreadyExistException, AuthorizationException, AuthenticationException, TokenExpiredException {
        return userConfig.admin.DeleteUserUseCase()
                .deleteById(id);
    }

    public UserDTO update(UpdateUserCommand updateUserCommand) throws AuthorizationException, PhoneIsRequiredException, TokenExpiredException, UserNotFoundException, UserWithPhoneAlreadyExistException, UserAlreadyExistException, AuthenticationException, EmailAndPasswordIsRequiredException {
        return userMapper.map(userConfig.admin.UpdateUserUseCase()
                .update(updateUserCommand));
    }

    public UserDTO changeUserPassword(ChangeUserPasswordCommand changeUserPasswordCommand) throws UserNotExistException, InvalidOldPasswordException, NewPasswordIsEmptyException {
        return userMapper.map(userConfig.admin.changeUserPasswordUseCase()
                .changePassword(changeUserPasswordCommand));
    }

    public UserDTO changeActiveStatus(String id) throws UserNotFoundException {
        return userMapper.map(userConfig.admin.changeUserActiveStatusUseCase()
                .changeActiveStatus(id));
    }

    public UserDTO deleteProfileImage(String id) throws UserNotFoundException {
        return userMapper.map(userConfig.admin.deleteUserProfileImageUseCase()
                .deleteProfileImage(id));
    }

//    public UserDTO updateUser(UserDTO userDTO) throws UserNotFoundException, UserAlreadyExistException, AuthorizationException, AuthenticationException, TokenExpiredException {
//        return userMapper.map(userConfig.admin.UpdateUserUseCase()
//                .update(userMapper.inverseMap(userDTO)));
//    }
//
//    public UserDTO update(String userId, UpdateUserCommand updateUserCommand) throws AuthorizationException, AuthenticationException, UserNotFoundException, TokenExpiredException {
//        return userMapper.map(userConfig.admin.UpdateUserUseCase()
//                .update(userId, updateUserCommand));
//    }

}
