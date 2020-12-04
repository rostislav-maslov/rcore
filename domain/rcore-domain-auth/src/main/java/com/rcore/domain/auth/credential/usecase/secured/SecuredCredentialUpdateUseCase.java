package com.rcore.domain.auth.credential.usecase.secured;

import com.rcore.commons.utils.StringUtils;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.exceptions.*;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.auth.credential.usecase.secured.commands.UpdateCredentialCommand;
import com.rcore.domain.security.port.CredentialVerifier;
import com.rcore.domain.security.usecase.secured.SecuredUpdateUseCase;

import java.util.Optional;

public class SecuredCredentialUpdateUseCase extends SecuredUpdateUseCase<String, CredentialEntity, CredentialRepository, UpdateCredentialCommand> {

    public SecuredCredentialUpdateUseCase(CredentialVerifier credentialVerifier, CredentialRepository repository) {
        super(credentialVerifier, repository);
    }

    @Override
    public CredentialEntity update(UpdateCredentialCommand updateCredentialCommand) throws Exception {
        checkAccess();

        CredentialEntity credentialEntity = repository.findById(updateCredentialCommand.getId())
                .orElseThrow(CredentialNotFoundException::new);

        validate(credentialEntity, updateCredentialCommand);

        return null;
    }

    private void validate(CredentialEntity credentialEntity, UpdateCredentialCommand updateCredentialCommand) throws CredentialWithUsernameAlreadyExistException, CredentialWithPhoneAlreadyExistException, CredentialWithEmailAlreadyExistException {
        if (StringUtils.hasText(updateCredentialCommand.getUsername())) {
            //Если в БД есть пользователь с username и его id не равен id изменяемого пользователя - ошибка
            Optional<CredentialEntity> credentialWithUsername = repository.findByUsername(updateCredentialCommand.getUsername());
            if (credentialWithUsername.isPresent() && !credentialWithUsername.get().getId().equals(credentialEntity.getId()))
                throw new CredentialWithUsernameAlreadyExistException();
        }

        if (StringUtils.hasText(updateCredentialCommand.getPhone())) {
            Optional<CredentialEntity> credentialWithPhone = repository.findByPhone(updateCredentialCommand.getPhone());
            //Если уже есть учетные данные с переданным phone - ошибка
            if (credentialWithPhone.isPresent() && !credentialWithPhone.get().getId().equals(credentialEntity.getId()))
                throw new CredentialWithPhoneAlreadyExistException();
        }

        if (StringUtils.hasText(updateCredentialCommand.getEmail())) {
            Optional<CredentialEntity> credentialWithEmail = repository.findByEmail(updateCredentialCommand.getEmail());
            //Если уже есть учетные данные с переданным phone - ошибка
            if (credentialWithEmail.isPresent() && !credentialWithEmail.get().getId().equals(credentialEntity.getId()))
                throw new CredentialWithEmailAlreadyExistException();
        }
    }
}
