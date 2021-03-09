package com.rcore.domain.user.usecase.admin;

import com.rcore.commons.utils.StringUtils;
import com.rcore.domain.phoneNumberFormat.exception.InvalidPhoneFormatForUpdateException;
import com.rcore.domain.phoneNumberFormat.validators.PhoneNumberValidator;
import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.role.port.RoleRepository;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.*;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.access.AdminUserUpdateAccess;
import com.rcore.domain.user.usecase.admin.commands.CreateUserCommand;
import com.rcore.domain.user.usecase.admin.commands.UpdateUserCommand;
import com.rcore.domain.user.validators.ChangeUserUseCaseValidator;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class UpdateUserUseCase extends AdminBaseUseCase {

    private final RoleRepository roleRepository;
    private final ChangeUserUseCaseValidator changeUserUseCaseValidator;
    private final PhoneNumberValidator phoneNumberValidator = new PhoneNumberValidator();

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

    public UserEntity update(UpdateUserCommand updateUserCommand) throws UserNotFoundException, AuthorizationException, TokenExpiredException, AuthenticationException, PhoneIsRequiredException, InvalidEmailException, UserAlreadyExistException, UserWithPhoneAlreadyExistException, InvalidLastNameException, InvalidRoleException, RoleIsRequiredException, InvalidFirstNameException, UserWithEmailAlreadyExistException, InvalidAccountStatusException, InvalidPhoneFormatForUpdateException {
        checkAccess();

        UserEntity userEntity = userRepository.findById(updateUserCommand.getId())
                .orElseThrow(UserNotFoundException::new);

        if (updateUserCommand.getRoles().isEmpty()) {
            throw new RoleIsRequiredException();
        }

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

        validate(updateUserCommand, userEntity);

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

        userEntity.setSecondName(updateUserCommand.getSecondName());

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

    private void validate(UpdateUserCommand updateUserCommand, UserEntity userEntity) throws InvalidFirstNameException, InvalidLastNameException, InvalidAccountStatusException, InvalidRoleException, RoleIsRequiredException, PhoneIsRequiredException, UserWithPhoneAlreadyExistException, UserWithEmailAlreadyExistException, InvalidEmailException, InvalidPhoneFormatForUpdateException {
        if (updateUserCommand.getPhone() != null && updateUserCommand.getPhoneNumberFormat() != null)
            if (phoneNumberValidator.validatePhone(updateUserCommand.getPhone(), updateUserCommand.getPhoneNumberFormat()))
                throw new InvalidPhoneFormatForUpdateException();

        //Проверка firstName
        if (!StringUtils.hasText(updateUserCommand.getFirstName()))
            throw new InvalidFirstNameException();

        //Проверка lastName
        if (!StringUtils.hasText(updateUserCommand.getLastName()))
            throw new InvalidLastNameException();

        if (updateUserCommand.getStatus() == null)
            throw new InvalidAccountStatusException();

        List<RoleEntity> roles = new ArrayList<>();

        //Проверка ролей
        if (updateUserCommand.getRoles() != null) {
            for (CreateUserCommand.Role role : updateUserCommand.getRoles()) {
                if (role.getId() != null)
                    roles.add(roleRepository.findById(role.getId())
                            .orElseThrow(InvalidRoleException::new));
                else if (role.getName() != null)
                    roles.add(roleRepository.findByName(role.getName())
                            .orElseThrow(InvalidRoleException::new));
            }
        }

        if (roles.isEmpty())
            throw new RoleIsRequiredException();

        //Достаем типы авторизации из ролей
        List<RoleEntity.AuthType> authTypes = roles
                .stream()
                .flatMap(r -> r.getAvailableAuthTypes().stream())
                .collect(Collectors.toList());

        //В зависимости от типов авторизации проверяем обязательные поля
        //Если тип SMS, то phone - обязателен
        if (authTypes.contains(RoleEntity.AuthType.SMS)) {
            if (updateUserCommand.getPhone() == null)
                throw new PhoneIsRequiredException();
        }
        //Если тип EMAIL, то email и password - обязательные поля
        if (authTypes.contains(RoleEntity.AuthType.EMAIL)) {
            if (!StringUtils.hasText(updateUserCommand.getEmail()))
                throw new InvalidEmailException();
        }

        if (updateUserCommand.getPhone() != null) {
            Optional<UserEntity> userWithTransferredNumber = userRepository.findByPhoneNumber(updateUserCommand.getPhone());
            if (updateUserCommand.getPhone() != null && userWithTransferredNumber.isPresent() && !userWithTransferredNumber.get().getId().equals(userEntity.getId()))
                throw new UserWithPhoneAlreadyExistException();
        }

        if (updateUserCommand.getEmail() != null) {
            Optional<UserEntity> userWithTransferredEmail = userRepository.findByEmail(updateUserCommand.getEmail());
            if (updateUserCommand.getEmail() != null && userWithTransferredEmail.isPresent() && !userWithTransferredEmail.get().getId().equals(userEntity.getId()))
                throw new UserWithEmailAlreadyExistException();
        }

    }

}