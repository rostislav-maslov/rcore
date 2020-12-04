package com.rcore.domain.auth.credential.usecase.secured;

import com.rcore.commons.utils.StringUtils;
import com.rcore.domain.auth.authorization.exceptions.PasswordIsRequiredException;
import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.exceptions.*;
import com.rcore.domain.auth.credential.port.CredentialIdGenerator;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.auth.credential.port.PasswordCryptographer;
import com.rcore.domain.auth.credential.usecase.secured.commands.CreateCredentialCommand;
import com.rcore.domain.security.port.CredentialVerifier;
import com.rcore.domain.security.usecase.secured.SecuredCreateUseCase;

/**
 * Создание учетных данных
 */
public class SecuredCredentialCreateUseCase extends SecuredCreateUseCase<String, CredentialIdGenerator<String>, CredentialEntity, CredentialRepository, CreateCredentialCommand> {

    private final PasswordCryptographer passwordCryptographer;

    public SecuredCredentialCreateUseCase(CredentialVerifier credentialVerifier, CredentialRepository repository, CredentialIdGenerator<String> idGenerator, PasswordCryptographer passwordCryptographer) {
        super(credentialVerifier, repository, idGenerator);
        this.passwordCryptographer = passwordCryptographer;
    }

    @Override
    public CredentialEntity create(CreateCredentialCommand createCredentialCommand) throws CredentialWithPhoneAlreadyExistException, CredentialWithUsernameAlreadyExistException, PasswordIsRequiredException, CredentialWithEmailAlreadyExistException, CredentialRoleIsRequiredException, CredentialStatusIsRequiredException {
        checkAccess();
        validate(createCredentialCommand);

        CredentialEntity credentialEntity = new CredentialEntity();
        credentialEntity.setId(idGenerator.generate());
        credentialEntity.setUsername(createCredentialCommand.getUsername());
        credentialEntity.setPassword(passwordCryptographer.encrypt(createCredentialCommand.getPassword(), credentialEntity.getId()));
        credentialEntity.setPhone(createCredentialCommand.getPhone());
        credentialEntity.setEmail(createCredentialCommand.getEmail());
        credentialEntity.setStatus(createCredentialCommand.getStatus());
        credentialEntity.setRoles(createCredentialCommand.getRoles());
        return credentialEntity;
    }

    private void validate(CreateCredentialCommand createCredentialCommand) throws CredentialWithUsernameAlreadyExistException, PasswordIsRequiredException, CredentialWithPhoneAlreadyExistException, CredentialWithEmailAlreadyExistException, CredentialRoleIsRequiredException, CredentialStatusIsRequiredException {
        if (StringUtils.hasText(createCredentialCommand.getUsername())) {
            //Если уже есть учетные данные с переданным username - ошибка
            if (repository.findByUsername(createCredentialCommand.getUsername()).isPresent())
                throw new CredentialWithUsernameAlreadyExistException();

            //Если передан username, то должен быть передан password для авторизации
            if (!StringUtils.hasText(createCredentialCommand.getPassword()))
                throw new PasswordIsRequiredException();
        }

        if (StringUtils.hasText(createCredentialCommand.getPhone())) {
            //Если уже есть учетные данные с переданным phone - ошибка
            if (repository.findByPhone(createCredentialCommand.getPhone()).isPresent())
                throw new CredentialWithPhoneAlreadyExistException();
        }

        if (StringUtils.hasText(createCredentialCommand.getEmail())) {
            //Если уже есть учетные данные с переданным email - ошибка
            if (repository.findByEmail(createCredentialCommand.getEmail()).isPresent())
                throw new CredentialWithEmailAlreadyExistException();
        }

        if (createCredentialCommand.getRoles() == null || createCredentialCommand.getRoles().isEmpty())
            throw new CredentialRoleIsRequiredException();

        if (createCredentialCommand.getStatus() == null)
            throw new CredentialStatusIsRequiredException();
    }
}
