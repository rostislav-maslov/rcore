package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.role.port.RoleRepository;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.*;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.access.AdminUserUpdateAccess;
import com.rcore.domain.user.usecase.admin.commands.UpdateUserCommand;
import com.rcore.domain.user.validators.ChangeUserUseCaseValidator;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UpdateUserUseCase extends AdminBaseUseCase {

    private final RoleRepository roleRepository;
    private final ChangeUserUseCaseValidator changeUserUseCaseValidator;

    public UpdateUserUseCase(UserRepository userRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase, RoleRepository roleRepository) {
        super(userRepository, new AdminUserUpdateAccess(), authorizationByTokenUseCase);
        this.roleRepository = roleRepository;
        this.changeUserUseCaseValidator = new ChangeUserUseCaseValidator(roleRepository, userRepository);
    }

    public UserEntity update(UserEntity userEntity) throws UserNotFoundException, AuthenticationException, AuthorizationException, TokenExpiredException {
        checkAccess();

        UserEntity old = userRepository.findById(userEntity.getId())
                .orElseThrow(() -> new UserNotFoundException());

        old.setFirstName(Optional.ofNullable(userEntity.getFirstName())
                .orElse(old.getFirstName()));

        old.setLastName(Optional.ofNullable(userEntity.getLastName())
                .orElse(old.getLastName()));

        old.setSecondName(Optional.ofNullable(userEntity.getSecondName())
                .orElse(old.getSecondName()));

        old.setFullName(Optional.ofNullable(userEntity.getFullName())
                .orElse(old.getFullName()));

        old.setStatus(Optional.ofNullable(userEntity.getStatus())
                .orElse(old.getStatus()));

        old.setUpdatedAt(LocalDateTime.now());

        old = userRepository.save(old);
        return old;
    }
//
//    public UserEntity update(String userId, UpdateUserCommand updateUserCommand) throws AuthenticationException, AuthorizationException, UserNotFoundException, TokenExpiredException {
//        checkAccess();
//
//        UserEntity old = userRepository.findById(userId)
//                .orElseThrow(() -> new UserNotFoundException());
//
//        old.setPhoneNumber(Optional.ofNullable(updateUserCommand.getPhoneNumber())
//                .orElse(old.getPhoneNumber()));
//
//        old.setFirstName(Optional.ofNullable(updateUserCommand.getFirstName())
//                .orElse(old.getFirstName()));
//
//        old.setLastName(Optional.ofNullable(updateUserCommand.getLastName())
//                .orElse(old.getLastName()));
//
//        old.setSecondName(Optional.ofNullable(updateUserCommand.getSecondName())
//                .orElse(old.getSecondName()));
//
//        old.setStatus(Optional.ofNullable(updateUserCommand.getStatus())
//                .orElse(old.getStatus()));
//
//        old.setUpdatedAt(LocalDateTime.now());
//
//        old = userRepository.save(old);
//        return old;
//    }

    public UserEntity update(UpdateUserCommand updateUserCommand) throws UserNotFoundException, AuthorizationException, TokenExpiredException, AuthenticationException, PhoneIsRequiredException, InvalidEmailException, UserAlreadyExistException, UserWithPhoneAlreadyExistException, InvalidLastNameException, InvalidRoleException, RoleIsRequiredException, InvalidFirstNameException, UserWithEmailAlreadyExistException, InvalidAccountStatusException {
        checkAccess();

        UserEntity userEntity = userRepository.findById(updateUserCommand.getId())
                .orElseThrow(UserNotFoundException::new);

        Set<RoleEntity> newRoles = updateUserCommand.getRoles()
                .stream()
                .map(role -> {
                    if (role.getId()!= null)
                        return roleRepository.findById(role.getId());
                    else if (role.getName() != null)
                        return roleRepository.findByName(role.getName());

                    return Optional.<RoleEntity>empty();
                })
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        changeUserUseCaseValidator.validate(userEntity, updateUserCommand);

        userEntity.setLogin(Optional.ofNullable(updateUserCommand.getLogin())
                .orElse(userEntity.getLogin()));

        userEntity.setPhoneNumber(Optional.ofNullable(updateUserCommand.getPhone())
                .orElse(userEntity.getPhoneNumber()));

        userEntity.setEmail(Optional.ofNullable(updateUserCommand.getEmail())
                .orElse(userEntity.getEmail()));

        userEntity.setFirstName(Optional.ofNullable(updateUserCommand.getFirstName())
                .orElse(userEntity.getFirstName()));

        userEntity.setLastName(Optional.ofNullable(updateUserCommand.getLastName())
                .orElse(userEntity.getLastName()));

        userEntity.setSecondName(Optional.ofNullable(updateUserCommand.getSecondName())
                .orElse(userEntity.getSecondName()));

        userEntity.setFullName(Stream.of(userEntity.getLastName(), userEntity.getFirstName(), userEntity.getSecondName())
                .filter(Objects::nonNull)
                .collect(Collectors.joining(" ")));

        userEntity.setProfileImageId(Optional.ofNullable(updateUserCommand.getProfileImageId())
                .orElse(userEntity.getProfileImageId()));

        userEntity.setStatus(Optional.ofNullable(updateUserCommand.getStatus())
                .orElse(userEntity.getStatus()));

        userEntity.setRoles(newRoles.isEmpty() ? userEntity.getRoles() : newRoles);

        userEntity.setCountryId(Optional.ofNullable(updateUserCommand.getCountryId())
                .orElse(userEntity.getCountryId()));

        userEntity.setUpdatedAt(LocalDateTime.now());
        userEntity = userRepository.save(userEntity);
        return userEntity;
    }

}