package com.rcore.domain.auth.authorization.usecase.secured;

import com.rcore.domain.auth.authorization.entity.AuthorizationEntity;
import com.rcore.domain.auth.authorization.usecase.AuthorizationUseCaseTestInfrastructure;
import com.rcore.domain.commons.port.SearchFilters;
import com.rcore.domain.commons.port.SearchResult;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

class SecuredAuthorizationViewUseCaseTestInfrastructure extends AuthorizationUseCaseTestInfrastructure {

    protected static final AuthorizationEntity authorization = AuthorizationEntity.builder()
            .id(UUID.randomUUID().toString())
            .build();

    protected static final AuthorizationEntity authorization1 = AuthorizationEntity.builder()
            .id(UUID.randomUUID().toString())
            .build();

    public SecuredAuthorizationViewUseCaseTestInfrastructure() {
        initAuthorizationMocks();
    }

    private void initAuthorizationMocks() {
        Mockito.when(authorizationRepository.findById(anyString()))
                .then(a -> {
                    if (a.getArgument(0).toString().equals(authorization.getId()))
                        return Optional.of(authorization);
                    else return Optional.empty();
                });

        Mockito.when(authorizationRepository.find(any(SearchFilters.class)))
                .thenAnswer(a -> SearchResult.withItemsAndCount(Arrays.asList(authorization, authorization1), 2l));
    }

}