package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.access.AdminChangeUserPasswordAccess;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.InvalidNewPasswordException;
import com.rcore.domain.user.exception.UserNotFoundException;
import com.rcore.domain.user.port.PasswordGenerator;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.usecase.admin.commands.ChangeUserPasswordCommand;

import java.time.LocalDateTime;

public class ChangeUserPasswordUseCase extends AdminBaseUseCase {

    private final PasswordGenerator passwordGenerator;

    public ChangeUserPasswordUseCase(UserRepository userRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase, PasswordGenerator passwordGenerator) {
        super(userRepository, new AdminChangeUserPasswordAccess(), authorizationByTokenUseCase);
        this.passwordGenerator = passwordGenerator;
    }

    public UserEntity changePassword(ChangeUserPasswordCommand changeUserPasswordCommand) throws UserNotFoundException, InvalidNewPasswordException {
        UserEntity userEntity = userRepository.findById(changeUserPasswordCommand.getId())
                .orElseThrow(UserNotFoundException::new);

        if (changeUserPasswordCommand.getNewPassword() == null || changeUserPasswordCommand.getNewPassword().length() == 0)
            throw new InvalidNewPasswordException();

        userEntity.setPassword(passwordGenerator.generate(userEntity.getId(), changeUserPasswordCommand.getNewPassword()));
        userEntity.setUpdatedAt(LocalDateTime.now());
        userEntity = userRepository.save(userEntity);
        return userEntity;
    }
}
