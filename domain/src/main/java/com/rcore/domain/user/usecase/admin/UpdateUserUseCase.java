package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.picture.port.PictureRepository;
import com.rcore.domain.picture.port.PictureStorage;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class UpdateUserUseCase extends AdminBaseUseCase {

    private final RoleRepository roleRepository;

    public UpdateUserUseCase(UserRepository userRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase, RoleRepository roleRepository) {
        super(userRepository, new AdminUserUpdateAccess(), authorizationByTokenUseCase);
        this.roleRepository = roleRepository;
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

    public UserEntity update(UpdateUserCommand updateUserCommand) throws UserNotFoundException, AuthorizationException, TokenExpiredException, AuthenticationException, PhoneIsRequiredException, EmailAndPasswordIsRequiredException, UserAlreadyExistException, UserWithPhoneAlreadyExistException {
        checkAccess();

        UserEntity userEntity = userRepository.findById(updateUserCommand.getId())
                .orElseThrow(UserNotFoundException::new);

        Set<RoleEntity> newRoles = updateUserCommand.getRoleIds()
                .stream()
                .map(roleId -> roleRepository.findById(roleId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

        validate(userEntity, updateUserCommand, newRoles);

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

        userEntity.setFullName(userEntity.getFullName());

        userEntity.setProfileImageId(Optional.ofNullable(updateUserCommand.getProfileImageId())
                .orElse(userEntity.getProfileImageId()));

        userEntity.setStatus(Optional.ofNullable(updateUserCommand.getStatus())
                .orElse(userEntity.getStatus()));

        userEntity.setCountryId(Optional.ofNullable(updateUserCommand.getCountryId())
                .orElse(userEntity.getCountryId()));

        userEntity = userRepository.save(userEntity);
        return userEntity;
    }

    private void validate(UserEntity userEntity, UpdateUserCommand updateUserCommand, Set<RoleEntity> newRoles) throws PhoneIsRequiredException, UserAlreadyExistException, UserWithPhoneAlreadyExistException, EmailAndPasswordIsRequiredException {
        if (!newRoles.isEmpty()) {
            //Достаем типы авторизации из ролей
            List<RoleEntity.AuthType> authTypes = newRoles
                    .stream()
                    .flatMap(r -> r.getAvailableAuthTypes().stream())
                    .collect(Collectors.toList());

            //В зависимости от типов авторизации проверяем обязательные поля
            //Если тип SMS, то phone - обязателен
            if (authTypes.contains(RoleEntity.AuthType.SMS)) {
                if (userEntity.getPhoneNumber() == null && updateUserCommand.getPhone() == null)
                    throw new PhoneIsRequiredException();

                if (userRepository.findByPhoneNumber(updateUserCommand.getPhone()).isPresent())
                    throw new UserWithPhoneAlreadyExistException();
            }
            //Если тип EMAIL, то email и password - обязательные поля
            else if (authTypes.contains(RoleEntity.AuthType.EMAIL)) {
                if (userEntity.getEmail() == null && updateUserCommand.getEmail() == null)
                    throw new EmailAndPasswordIsRequiredException();

                if (userRepository.findByEmail(updateUserCommand.getEmail()).isPresent())
                    throw new UserAlreadyExistException();
            }
        }

    }

}