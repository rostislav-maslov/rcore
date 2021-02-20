package com.rcore.domain.auth.credential.usecases;

import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.commons.port.dto.SearchFilters;
import com.rcore.domain.commons.port.dto.SearchResult;
import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.rcore.domain.commons.usecase.AbstractFindWithFiltersUseCase;
import com.rcore.domain.commons.usecase.model.FiltersInputValues;
import com.rcore.domain.commons.usecase.model.IdInputValues;
import com.rcore.domain.commons.usecase.model.SearchResultEntityOutputValues;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class FindCredentialsWithFiltersUseCaseTest extends FindCredentialsWithFiltersUseCaseTestInfrastructure {

    @Test
    public void test() {
        SearchResult<CredentialEntity> result = findCredentialsWithFiltersUseCase
                .execute(FiltersInputValues.of(new SearchFilters()))
                .getResult();
        Assertions.assertNotNull(result);
    }

    @Test
    public void testById() {
        Optional<CredentialEntity> result = findCredentialByIdUseCase
                .execute(IdInputValues.of(credential.getId()))
                .getEntity();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.get().getId(), credential.getId());
    }

}