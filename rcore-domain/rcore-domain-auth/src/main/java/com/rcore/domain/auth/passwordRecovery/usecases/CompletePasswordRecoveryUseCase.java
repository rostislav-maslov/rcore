package com.rcore.domain.auth.passwordRecovery.usecases;

import com.rcore.domain.auth.credential.usecases.ChangeCredentialPasswordUseCase;
import com.rcore.domain.auth.passwordRecovery.entity.PasswordRecoveryEntity;
import com.rcore.domain.auth.passwordRecovery.exceptions.PasswordRecoveryNotFoundException;
import com.rcore.domain.auth.passwordRecovery.port.PasswordRecoveryRepository;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class CompletePasswordRecoveryUseCase extends UseCase<CompletePasswordRecoveryUseCase.InputValues, SingletonEntityOutputValues<PasswordRecoveryEntity>> {

    private final PasswordRecoveryRepository passwordRecoveryRepository;
    private final ChangeCredentialPasswordUseCase changeCredentialPasswordUseCase;

    @Override
    public SingletonEntityOutputValues<PasswordRecoveryEntity> execute(InputValues inputValues) {
        PasswordRecoveryEntity passwordRecoveryEntity = passwordRecoveryRepository.findById(inputValues.getId())
                .orElseThrow(() -> new PasswordRecoveryNotFoundException(inputValues.getId()));

        changeCredentialPasswordUseCase.execute(ChangeCredentialPasswordUseCase.InputValues.of(passwordRecoveryEntity.getCredentialId(), inputValues.getNewPassword()));
        passwordRecoveryEntity.setCompleted();
        return SingletonEntityOutputValues.of(passwordRecoveryRepository.save(passwordRecoveryEntity));
    }

    @Value(staticConstructor = "of")
    public static class InputValues implements UseCase.InputValues {
        private final String id;
        private final String newPassword;
    }

}
