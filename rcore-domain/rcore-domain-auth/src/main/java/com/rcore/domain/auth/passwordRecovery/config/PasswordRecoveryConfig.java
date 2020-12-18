package com.rcore.domain.auth.passwordRecovery.config;

import com.rcore.domain.auth.credential.usecases.ClearCredentialPasswordUseCase;
import com.rcore.domain.auth.credential.usecases.ChangeCredentialPasswordUseCase;
import com.rcore.domain.auth.credential.usecases.FindCredentialByEmailUseCase;
import com.rcore.domain.auth.passwordRecovery.port.PasswordRecoveryIdGenerator;
import com.rcore.domain.auth.passwordRecovery.port.PasswordRecoveryRepository;
import com.rcore.domain.auth.passwordRecovery.usecases.CompletePasswordRecoveryUseCase;
import com.rcore.domain.auth.passwordRecovery.usecases.FindPasswordRecoveryByIdUseCase;
import com.rcore.domain.auth.passwordRecovery.usecases.FindPasswordRecoveryWithFiltersUseCase;
import com.rcore.domain.auth.passwordRecovery.usecases.InitPasswordRecoveryUseCase;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PasswordRecoveryConfig {
    private final PasswordRecoveryRepository passwordRecoveryRepository;
    private final PasswordRecoveryIdGenerator passwordRecoveryIdGenerator;
    private final ClearCredentialPasswordUseCase clearCredentialPasswordUseCase;
    private final ChangeCredentialPasswordUseCase changeCredentialPasswordUseCase;
    private final FindCredentialByEmailUseCase findCredentialByEmailUseCase;

    public InitPasswordRecoveryUseCase initPasswordRecoveryUseCase() {
        return new InitPasswordRecoveryUseCase(passwordRecoveryRepository, passwordRecoveryIdGenerator, clearCredentialPasswordUseCase, findCredentialByEmailUseCase);
    }

    public FindPasswordRecoveryByIdUseCase findPasswordRecoveryByIdUseCase() {
        return new FindPasswordRecoveryByIdUseCase(passwordRecoveryRepository);
    }

    public FindPasswordRecoveryWithFiltersUseCase findPasswordRecoveryWithFiltersUseCase() {
        return new FindPasswordRecoveryWithFiltersUseCase(passwordRecoveryRepository);
    }

    public CompletePasswordRecoveryUseCase completePasswordRecoveryUseCase() {
        return new CompletePasswordRecoveryUseCase(passwordRecoveryRepository, changeCredentialPasswordUseCase);
    }
}
