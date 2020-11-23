package com.rcore.domain.user.usecase.admin;

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

import java.util.Optional;

public class UpdateCurrentUserUseCase extends AdminBaseUseCase {

    private final RoleRepository roleRepository;
    private final ChangeUserUseCaseValidator changeUserUseCaseValidator;

    public UpdateCurrentUserUseCase(UserRepository userRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase, RoleRepository roleRepository) {
        super(userRepository, new AdminUpdateCurrentUserAccess(), authorizationByTokenUseCase);
        this.roleRepository = roleRepository;
        changeUserUseCaseValidator = new ChangeUserUseCaseValidator(roleRepository, userRepository);
    }

    public UserEntity update(CreateUserCommand createUserCommand) throws AuthorizationException, TokenExpiredException, AuthenticationException, UserNotFoundException, InvalidLastNameException, PhoneIsRequiredException, UserAlreadyExistException, InvalidFirstNameException, InvalidRoleException, UserWithPhoneAlreadyExistException, RoleIsRequiredException, InvalidEmailException, UserWithEmailAlreadyExistException {
        String userId = checkAccess().getId();
        UserEntity userEntity = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        changeUserUseCaseValidator.validate(createUserCommand);

        userEntity.setLogin(Optional.ofNullable(createUserCommand.getLogin())
                .orElse(userEntity.getLogin()));

        userEntity.setPhoneNumber(Optional.ofNullable(createUserCommand.getPhone())
                .orElse(userEntity.getPhoneNumber()));

        userEntity.setEmail(Optional.ofNullable(createUserCommand.getEmail())
                .orElse(userEntity.getEmail()));

        userEntity.setFirstName(Optional.ofNullable(createUserCommand.getFirstName())
                .orElse(userEntity.getFirstName()));

        userEntity.setLastName(Optional.ofNullable(createUserCommand.getLastName())
                .orElse(userEntity.getLastName()));

        userEntity.setSecondName(Optional.ofNullable(createUserCommand.getSecondName())
                .orElse(userEntity.getSecondName()));

        userEntity.setFullName(userEntity.getFullName());

        userEntity.setProfileImageId(Optional.ofNullable(createUserCommand.getProfileImageId())
                .orElse(userEntity.getProfileImageId()));

        userEntity.setStatus(Optional.ofNullable(createUserCommand.getStatus())
                .orElse(userEntity.getStatus()));

        userEntity.setCountryId(Optional.ofNullable(createUserCommand.getCountryId())
                .orElse(userEntity.getCountryId()));

        userEntity = userRepository.save(userEntity);
        return userRepository.save(userEntity);

    }
}
