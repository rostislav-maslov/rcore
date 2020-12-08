package com.rcore.domain.auth.credential.usecases;

import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.commons.port.dto.SearchFilters;
import com.rcore.domain.commons.port.dto.SearchResult;
import com.rcore.domain.commons.usecase.AbstractFindByIdUseCase;
import com.rcore.domain.commons.usecase.AbstractFindWithFiltersUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class FindCredentialsWithFiltersUseCaseTest extends FindCredentialsWithFiltersUseCaseTestInfrastructure {

    @Test
    public void test() {
        SearchResult<CredentialEntity> result = findCredentialsWithFiltersUseCase
                .execute(new AbstractFindWithFiltersUseCase.InputValues<>(new SearchFilters()))
                .getResult();
        Assertions.assertNotNull(result);
    }

    @Test
    public void testById() {
        Optional<CredentialEntity> result = findCredentialByIdUseCase
                .execute(AbstractFindByIdUseCase.InputValues.of(credential.getId()))
                .getResult();

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.get().getId(), credential.getId());
    }

}