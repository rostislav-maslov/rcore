package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.access.AdminChangeUserPasswordAccess;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.InvalidNewPasswordException;
import com.rcore.domain.user.exception.InvalidOldPasswordException;
import com.rcore.domain.user.exception.TokenExpiredException;
import com.rcore.domain.user.exception.UserNotExistException;
import com.rcore.domain.user.port.PasswordGenerator;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.usecase.admin.commands.ChangeCurrentUserPasswordCommand;

import java.time.LocalDateTime;

public class ChangeCurrentUserPasswordUseCase extends AdminBaseUseCase {

    private final PasswordGenerator passwordGenerator;

    public ChangeCurrentUserPasswordUseCase(UserRepository userRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase, PasswordGenerator passwordGenerator) {
        super(userRepository, new AdminChangeUserPasswordAccess(), authorizationByTokenUseCase);
        this.passwordGenerator = passwordGenerator;
    }

    public UserEntity changePassword(ChangeCurrentUserPasswordCommand changeCurrentUserPasswordCommand) throws UserNotExistException, InvalidOldPasswordException, InvalidNewPasswordException, AuthorizationException, TokenExpiredException, AuthenticationException {
        UserEntity userEntity = checkAccess();

        if (!passwordGenerator.check(userEntity.getId(), changeCurrentUserPasswordCommand.getOldPassword(), userEntity.getPassword()))
            throw new InvalidOldPasswordException();

        if (changeCurrentUserPasswordCommand.getNewPassword() == null || changeCurrentUserPasswordCommand.getNewPassword().length() == 0)
            throw new InvalidNewPasswordException();

        userEntity.setPassword(passwordGenerator.generate(userEntity.getId(), changeCurrentUserPasswordCommand.getNewPassword()));

        userEntity.setUpdatedAt(LocalDateTime.now());
        userEntity = userRepository.save(userEntity);
        return userEntity;
    }
}
