package com.rcore.domain.auth.credential.usecases;

import com.rcore.domain.auth.credential.entity.CredentialEntity;
import com.rcore.domain.auth.credential.port.CredentialRepository;
import com.rcore.domain.commons.port.dto.SearchResult;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;

public class FindCredentialsWithFiltersUseCaseTestInfrastructure {

    protected static CredentialEntity credential = CredentialEntity.builder()
            .id(UUID.randomUUID().toString())
            .build();

    protected final CredentialRepository credentialRepository = Mockito.mock(CredentialRepository.class);
    protected final FindCredentialsWithFiltersUseCase findCredentialsWithFiltersUseCase = new FindCredentialsWithFiltersUseCase(credentialRepository);
    protected final FindCredentialByIdUseCase findCredentialByIdUseCase = new FindCredentialByIdUseCase(credentialRepository);

    public FindCredentialsWithFiltersUseCaseTestInfrastructure() {
        initMocks();
    }

    protected void initMocks() {
        Mockito.when(credentialRepository.find(any()))
                .thenAnswer(a -> SearchResult.withItemsAndCount(
                        Arrays.asList(credential),
                        1l
                ));

        Mockito.when(credentialRepository.findById(anyString()))
                .thenAnswer(a -> Optional.of(credential));
    }

}
