package com.rcore.domain.auth.credential.usecases;

import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.exceptions.CredentialNotFoundException;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.IdInputValues;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.*;

@RequiredArgsConstructor
public class ChangeCredentialStatusUseCase extends UseCase<IdInputValues<String>, SingletonEntityOutputValues<CredentialEntity>> {

    private final CredentialRepository credentialRepository;

    @Override
    public SingletonEntityOutputValues<CredentialEntity> execute(IdInputValues<String> stringIdInputValues) {
        CredentialEntity entity = (credentialRepository.findById(stringIdInputValues.getId())
                .orElseThrow(CredentialNotFoundException::new));

        entity.changeStatus();

        return SingletonEntityOutputValues.of(credentialRepository.save(entity));
    }
}
