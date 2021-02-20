package com.rcore.domain.auth.passwordRecovery.usecases;

import com.rcore.domain.auth.passwordRecovery.entity.PasswordRecoveryEntity;
import com.rcore.domain.auth.passwordRecovery.port.PasswordRecoveryRepository;
import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;

public class FindPasswordRecoveryByIdUseCase extends AbstractFindByIdUseCase<String, PasswordRecoveryEntity, PasswordRecoveryRepository> {

    public FindPasswordRecoveryByIdUseCase(PasswordRecoveryRepository repository) {
        super(repository);
    }
}
