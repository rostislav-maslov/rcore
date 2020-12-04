package com.rcore.domain.auth.authorization.usecase.secured;

import com.rcore.domain.auth.authorization.entity.AuthorizationEntity;
import com.rcore.domain.commons.port.SearchFilters;
import com.rcore.domain.commons.port.SearchResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Optional;

class SecuredAuthorizationViewUseCaseTest extends SecuredAuthorizationViewUseCaseTestInfrastructure {

    @Test
    void testSuccessfulFindById() {
        Optional<AuthorizationEntity> result = authorizationConfig.secured.viewUseCase()
                .findById(authorization.getId());

        Assertions.assertNotNull(result);
        Assertions.assertNotNull(result.get());
        Assertions.assertEquals(result.get().getId(), authorization.getId());
    }

    @Test
    void testSuccessfulFindByIdWithNullId() {
        Optional<AuthorizationEntity> result = authorizationConfig.secured.viewUseCase()
                .findById(null);

        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void testSuccessfulFindWithFilters() {
        SearchResult<AuthorizationEntity> result = authorizationConfig.secured.viewUseCase()
                .findWithFilters(SearchFilters.builder().build());

        Assertions.assertNotNull(result);
        Assertions.assertEquals(result.getCount(), 2l);
    }

}