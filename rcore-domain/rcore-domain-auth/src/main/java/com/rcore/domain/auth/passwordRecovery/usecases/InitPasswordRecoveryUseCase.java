package com.rcore.domain.auth.passwordRecovery.usecases;

import com.rcore.domain.auth.credential.usecases.ClearCredentialPasswordUseCase;
import com.rcore.domain.auth.passwordRecovery.entity.PasswordRecoveryEntity;
import com.rcore.domain.auth.passwordRecovery.port.PasswordRecoveryIdGenerator;
import com.rcore.domain.auth.passwordRecovery.port.PasswordRecoveryRepository;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.IdInputValues;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class InitPasswordRecoveryUseCase extends UseCase<InitPasswordRecoveryUseCase.InputValues, SingletonEntityOutputValues<PasswordRecoveryEntity>> {

    private final PasswordRecoveryRepository passwordRecoveryRepository;
    private final PasswordRecoveryIdGenerator passwordRecoveryIdGenerator;
    private final ClearCredentialPasswordUseCase clearCredentialPasswordUseCase;

    @Override
    public SingletonEntityOutputValues<PasswordRecoveryEntity> execute(InputValues inputValues) {
        clearCredentialPasswordUseCase.execute(IdInputValues.of(inputValues.getCredentialId()));

        PasswordRecoveryEntity passwordRecoveryEntity = new PasswordRecoveryEntity();
        passwordRecoveryEntity.setId(passwordRecoveryIdGenerator.generate());
        passwordRecoveryEntity.setCredentialId(inputValues.getCredentialId());

        return SingletonEntityOutputValues.of(passwordRecoveryRepository.save(passwordRecoveryEntity));
    }

    @Value(staticConstructor = "of")
    public static class InputValues implements UseCase.InputValues {
        private final String credentialId;
    }

}
