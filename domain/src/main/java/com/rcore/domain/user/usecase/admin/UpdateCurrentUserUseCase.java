package com.rcore.domain.user.usecase.admin;

import com.rcore.commons.utils.StringUtils;
import com.rcore.domain.phoneNumberFormat.exception.InvalidPhoneFormatForUpdateException;
import com.rcore.domain.role.port.RoleRepository;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.access.AdminUpdateCurrentUserAccess;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.*;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.usecase.admin.commands.CreateUserCommand;
import com.rcore.domain.user.validators.ChangeUserUseCaseValidator;

import java.time.LocalDateTime;
import java.util.Objects;

public class UpdateCurrentUserUseCase extends AdminBaseUseCase {

    private final ChangeUserUseCaseValidator changeUserUseCaseValidator;

    public UpdateCurrentUserUseCase(UserRepository userRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase, RoleRepository roleRepository) {
        super(userRepository, new AdminUpdateCurrentUserAccess(), authorizationByTokenUseCase);
        changeUserUseCaseValidator = new ChangeUserUseCaseValidator(roleRepository, userRepository);
    }

    public UserEntity update(CreateUserCommand createUserCommand) throws AuthorizationException, TokenExpiredException, AuthenticationException, UserNotFoundException, InvalidLastNameException, PhoneIsRequiredException, UserAlreadyExistException, InvalidFirstNameException, InvalidRoleException, UserWithPhoneAlreadyExistException, RoleIsRequiredException, InvalidEmailException, UserWithEmailAlreadyExistException, InvalidAccountStatusException, InvalidPhoneFormatForUpdateException {
        UserEntity userEntity = checkAccess();

        changeUserUseCaseValidator.validate(userEntity, createUserCommand);

        userEntity.setLogin(StringUtils.hasText(createUserCommand.getLogin())
                ? createUserCommand.getLogin()
                : userEntity.getLogin());

        userEntity.setPhoneNumber(createUserCommand.getPhone() != null
                ? createUserCommand.getPhone()
                : userEntity.getPhoneNumber());

        userEntity.setEmail(StringUtils.hasText(createUserCommand.getEmail())
                ? createUserCommand.getEmail()
                : userEntity.getEmail());

        userEntity.setFirstName(StringUtils.hasText(createUserCommand.getFirstName())
                ? createUserCommand.getFirstName()
                : userEntity.getFirstName());

        userEntity.setLastName(StringUtils.hasText(createUserCommand.getLastName())
                ? createUserCommand.getLastName()
                : userEntity.getLastName());

        userEntity.setSecondName(createUserCommand.getSecondName());

        userEntity.setFullName(userEntity.getFullName());

        userEntity.setProfileImageId(StringUtils.hasText(createUserCommand.getProfileImageId())
                ? createUserCommand.getProfileImageId()
                : userEntity.getProfileImageId());

        userEntity.setStatus(Objects.nonNull(createUserCommand.getStatus())
                ? createUserCommand.getStatus()
                : userEntity.getStatus());

        userEntity.setCountryId(StringUtils.hasText(createUserCommand.getCountryId())
                ? createUserCommand.getCountryId()
                : userEntity.getCountryId());

        userEntity.setUpdatedAt(LocalDateTime.now());
        userEntity = userRepository.save(userEntity);
        return userEntity;

    }
}
