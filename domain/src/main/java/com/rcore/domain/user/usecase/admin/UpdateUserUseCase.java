package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.UserAlreadyExistException;
import com.rcore.domain.user.exception.UserNotFoundException;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.access.AdminUserUpdateAccess;
import com.rcore.domain.user.usecase.admin.dto.UpdateUserFields;

import java.time.LocalDateTime;
import java.util.Optional;

public class UpdateUserUseCase extends AdminBaseUseCase {

    public UpdateUserUseCase(UserRepository userRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase) {
        super(userRepository, new AdminUserUpdateAccess(), authorizationByTokenUseCase);
    }

    public UserEntity update(UserEntity userEntity) throws UserNotFoundException, AuthenticationException, AuthorizationException {
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

    public UserEntity update(String userId, UpdateUserFields updateUserFields) throws AuthenticationException, AuthorizationException, UserNotFoundException {
        checkAccess();

        UserEntity old = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException());

        old.setPhoneNumber(Optional.ofNullable(updateUserFields.getPhoneNumber())
                .orElse(old.getPhoneNumber()));

        old.setFirstName(Optional.ofNullable(updateUserFields.getFirstName())
                .orElse(old.getFirstName()));

        old.setLastName(Optional.ofNullable(updateUserFields.getLastName())
                .orElse(old.getLastName()));

        old.setSecondName(Optional.ofNullable(updateUserFields.getSecondName())
                .orElse(old.getSecondName()));

        old.setStatus(Optional.ofNullable(updateUserFields.getStatus())
                .orElse(old.getStatus()));

        old.setUpdatedAt(LocalDateTime.now());

        old = userRepository.save(old);
        return old;
    }

}