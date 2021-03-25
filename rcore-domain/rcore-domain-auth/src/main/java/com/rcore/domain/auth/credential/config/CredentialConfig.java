package com.rcore.domain.auth.credential.config;

import com.rcore.domain.auth.credential.port.CredentialIdGenerator;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.auth.credential.port.PasswordCryptographer;
import com.rcore.domain.auth.credential.usecases.*;
import com.rcore.domain.auth.role.usecases.FindRoleByIdUseCase;
import com.rcore.domain.auth.role.usecases.FindRoleByNameUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CredentialConfig {

    private final CredentialRepository credentialRepository;
    private final CredentialIdGenerator credentialIdGenerator;
    private final PasswordCryptographer passwordCryptographer;
    private final FindRoleByIdUseCase findRoleByIdUseCase;
    private final FindRoleByNameUseCase findRoleByNameUseCase;

    public ChangeCredentialStatusUseCase changeCredentialStatusUseCase() {
        return new ChangeCredentialStatusUseCase(credentialRepository);
    }

    public CreateCredentialUseCase createCredentialUseCase() {
        return new CreateCredentialUseCase(credentialRepository, credentialIdGenerator, findRoleByIdUseCase, findRoleByNameUseCase, findCredentialByPhoneUseCase(), findCredentialByEmailUseCase(), findCredentialByUsernameUseCase(), passwordCryptographer);
    }

    public FindCredentialByEmailUseCase findCredentialByEmailUseCase() {
        return new FindCredentialByEmailUseCase(credentialRepository);
    }

    public FindCredentialByIdUseCase findCredentialByIdUseCase() {
        return new FindCredentialByIdUseCase(credentialRepository);
    }

    public FindCredentialByPhoneUseCase findCredentialByPhoneUseCase() {
        return new FindCredentialByPhoneUseCase(credentialRepository);
    }

    public FindCredentialByUsernameUseCase findCredentialByUsernameUseCase() {
        return new FindCredentialByUsernameUseCase(credentialRepository);
    }

    public FindCredentialsWithFiltersUseCase findCredentialsWithFiltersUseCase() {
        return new FindCredentialsWithFiltersUseCase(credentialRepository);
    }

    public UpdateCredentialByOwnerUseCase updateCredentialByOwnerUseCase() {
        return new UpdateCredentialByOwnerUseCase(credentialRepository, findCredentialByPhoneUseCase(), findCredentialByEmailUseCase(), findCredentialByUsernameUseCase());
    }

    public UpdateCredentialUseCase updateCredentialUseCase() {
        return new UpdateCredentialUseCase(credentialRepository, findRoleByIdUseCase, findRoleByNameUseCase, findCredentialByPhoneUseCase(), findCredentialByUsernameUseCase());
    }

    public ChangeCredentialPasswordUseCase changeCredentialPasswordUseCase() {
        return new ChangeCredentialPasswordUseCase(credentialRepository, passwordCryptographer);
    }

}
