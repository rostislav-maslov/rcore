package com.rcore.domain.auth.credential.usecases;

import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.commons.port.SearchResult;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

public class FindCredentialsWithFiltersUseCaseTestInfrastructure {

    protected static CredentialEntity credential = CredentialEntity.builder()
            .id(UUID.randomUUID().toString())
            .build();

    protected final CredentialRepository credentialRepository = Mockito.mock(CredentialRepository.class);

    protected void initMocks() {

        Mockito.when(credentialRepository.find(any()))
                .thenAnswer(a -> SearchResult.withItemsAndCount(
                        Arrays.asList(credential),
                    1l
            )
        );
    }

}
