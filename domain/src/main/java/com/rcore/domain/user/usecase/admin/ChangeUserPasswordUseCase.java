package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.access.AdminChangeUserPasswordAccess;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.NewPasswordIsEmptyException;
import com.rcore.domain.user.exception.InvalidOldPasswordException;
import com.rcore.domain.user.exception.UserNotExistException;
import com.rcore.domain.user.port.PasswordGenerator;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.usecase.admin.commands.ChangeUserPasswordCommand;

public class ChangeUserPasswordUseCase extends AdminBaseUseCase {

    private final PasswordGenerator passwordGenerator;

    public ChangeUserPasswordUseCase(UserRepository userRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase, PasswordGenerator passwordGenerator) {
        super(userRepository, new AdminChangeUserPasswordAccess(), authorizationByTokenUseCase);
        this.passwordGenerator = passwordGenerator;
    }

    public UserEntity changePassword(ChangeUserPasswordCommand changeUserPasswordCommand) throws UserNotExistException, InvalidOldPasswordException, NewPasswordIsEmptyException {
        UserEntity userEntity = userRepository.findById(changeUserPasswordCommand.getId())
                .orElseThrow(UserNotExistException::new);

        String protectedOldPassword = passwordGenerator.generate(userEntity.getId(), changeUserPasswordCommand.getOldPassword());
        if (!protectedOldPassword.equals(userEntity.getPassword()))
            throw new InvalidOldPasswordException();

        if (changeUserPasswordCommand.getNewPassword() == null || changeUserPasswordCommand.getNewPassword().length() == 0)
            throw new NewPasswordIsEmptyException();

        userEntity.setPassword(passwordGenerator.generate(userEntity.getId(), changeUserPasswordCommand.getNewPassword()));
        userEntity = userRepository.save(userEntity);
        return userEntity;
    }
}
