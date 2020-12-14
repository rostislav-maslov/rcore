package com.rcore.domain.auth.credential.usecases;

import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.commons.usecase.UseCase;
import com.rcore.domain.commons.usecase.model.IdInputValues;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ChangeCredentialStatusUseCase extends UseCase<IdInputValues<String>, SingletonEntityOutputValues<CredentialEntity>> {

    private final CredentialRepository credentialRepository;

    @Override
    public SingletonEntityOutputValues<CredentialEntity> execute(IdInputValues<String> stringIdInputValues) {
        return null;
    }
}
