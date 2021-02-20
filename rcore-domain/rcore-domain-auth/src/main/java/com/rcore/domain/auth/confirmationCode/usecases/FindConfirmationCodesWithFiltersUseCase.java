package com.rcore.domain.auth.confirmationCode.usecases;

import com.rcore.domain.auth.confirmationCode.entity.ConfirmationCodeEntity;
import com.rcore.domain.auth.confirmationCode.port.ConfirmationCodeRepository;
import com.rcore.domain.commons.port.dto.SearchFilters;
import com.rcore.domain.commons.usecase.AbstractFindWithFiltersUseCase;

public class FindConfirmationCodesWithFiltersUseCase extends AbstractFindWithFiltersUseCase<ConfirmationCodeEntity, SearchFilters, ConfirmationCodeRepository> {

    public FindConfirmationCodesWithFiltersUseCase(ConfirmationCodeRepository repository) {
        super(repository);
    }
}
