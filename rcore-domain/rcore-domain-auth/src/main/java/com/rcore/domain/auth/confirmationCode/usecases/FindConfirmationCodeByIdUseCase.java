package com.rcore.domain.auth.confirmationCode.usecases;

import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;
import com.rcore.domain.auth.confirmationCode.port.ConfirmationCodeRepository;
import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;

public class FindConfirmationCodeByIdUseCase extends AbstractFindByIdUseCase<String, ConfirmationCodeEntity, ConfirmationCodeRepository> {

    public FindConfirmationCodeByIdUseCase(ConfirmationCodeRepository repository) {
        super(repository);
    }
}
