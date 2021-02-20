package com.rcore.domain.auth.passwordRecovery.usecases;

import com.rcore.domain.auth.passwordRecovery.entity.PasswordRecoveryEntity;
import com.rcore.domain.auth.passwordRecovery.port.PasswordRecoveryRepository;
import com.rcore.domain.commons.port.dto.SearchFilters;
import com.rcore.domain.commons.usecase.AbstractFindWithFiltersUseCase;

public class FindPasswordRecoveryWithFiltersUseCase extends AbstractFindWithFiltersUseCase<PasswordRecoveryEntity, SearchFilters, PasswordRecoveryRepository> {

    public FindPasswordRecoveryWithFiltersUseCase(PasswordRecoveryRepository repository) {
        super(repository);
    }
}
