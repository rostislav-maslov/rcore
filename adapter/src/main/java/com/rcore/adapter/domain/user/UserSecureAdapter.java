package com.rcore.adapter.domain.user;

import com.rcore.adapter.domain.role.mapper.RoleMapper;
import com.rcore.adapter.domain.user.dto.UserDTO;
import com.rcore.adapter.domain.user.mapper.UserMapper;
import com.rcore.domain.phoneNumberFormat.exception.InvalidPhoneFormatForCreateException;
import com.rcore.domain.phoneNumberFormat.exception.InvalidPhoneFormatForUpdateException;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.config.UserConfig;
import com.rcore.domain.user.exception.*;
import com.rcore.domain.user.usecase.admin.commands.ChangeCurrentUserPasswordCommand;
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

    public UserDTO create(CreateUserCommand createUserCommand) throws AuthorizationException, RoleIsRequiredException, PhoneIsRequiredException, TokenExpiredException, InvalidEmailException, UserWithPhoneAlreadyExistException, UserAlreadyExistException, AuthenticationException, InvalidFirstNameException, InvalidLastNameException, InvalidRoleException, UserWithEmailAlreadyExistException, InvalidAccountStatusException, InvalidPhoneFormatForCreateException {
        return userMapper.map(userConfig.admin.CreateUseCase()
                .create(createUserCommand));
    }

    public Boolean deleteUser(String id) throws AuthorizationException, AuthenticationException, TokenExpiredException {
        return userConfig.admin.DeleteUserUseCase()
                .deleteById(id);
    }

    public UserDTO update(UpdateUserCommand updateUserCommand) throws AuthorizationException, PhoneIsRequiredException, TokenExpiredException, UserNotFoundException, UserWithPhoneAlreadyExistException, UserAlreadyExistException, AuthenticationException, InvalidEmailException, InvalidRoleException, InvalidFirstNameException, RoleIsRequiredException, InvalidLastNameException, UserWithEmailAlreadyExistException, InvalidAccountStatusException, InvalidPhoneFormatForUpdateException {
        return userMapper.map(userConfig.admin.UpdateUserUseCase()
                .update(updateUserCommand));
    }

    public UserDTO updateCurrentUser(CreateUserCommand createUserCommand) throws UserNotFoundException, PhoneIsRequiredException, AuthenticationException, TokenExpiredException, InvalidLastNameException, InvalidRoleException, UserWithPhoneAlreadyExistException, AuthorizationException, InvalidEmailException, UserAlreadyExistException, InvalidFirstNameException, RoleIsRequiredException, UserWithEmailAlreadyExistException, InvalidAccountStatusException, InvalidPhoneFormatForUpdateException {
        return userMapper.map(userConfig.admin.updateCurrentUserUseCase()
                .update(createUserCommand));
    }

    public UserDTO changeCurrentUserPassword(ChangeCurrentUserPasswordCommand changeCurrentUserPasswordCommand) throws UserNotExistException, InvalidOldPasswordException, InvalidNewPasswordException, AuthorizationException, TokenExpiredException, AuthenticationException {
        return userMapper.map(userConfig.admin.changeCurrentUserPasswordUseCase()
                .changePassword(changeCurrentUserPasswordCommand));
    }

    public UserDTO changeUserPassword(ChangeUserPasswordCommand changeUserPasswordCommand) throws InvalidNewPasswordException, UserNotFoundException {
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

    public UserDTO deleteCurrentUserProfileImage() throws AuthorizationException, TokenExpiredException, AuthenticationException {
        return userMapper.map(userConfig.admin.deleteCurrentUserProfileImageUseCase()
                .deleteProfileImage());
    }

}
