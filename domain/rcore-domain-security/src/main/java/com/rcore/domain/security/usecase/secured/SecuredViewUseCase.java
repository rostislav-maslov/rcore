package com.rcore.domain.security.usecase.secured;

import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.commons.port.ReadRepository;
import com.rcore.domain.commons.port.SearchFilters;
import com.rcore.domain.commons.port.SearchResult;
import com.rcore.domain.commons.usecase.IViewUseCase;
import com.rcore.domain.security.port.CredentialVerifier;

import java.util.Optional;

public abstract class SecuredViewUseCase<Id, Entity extends BaseEntity, Filters extends SearchFilters, Repository extends ReadRepository<Id, Entity, Filters>>
        extends SecuredUseCase implements IViewUseCase<Id, Entity, Filters> {

    protected final Repository repository;

    public SecuredViewUseCase(CredentialVerifier credentialVerifier, Repository repository) {
        super(credentialVerifier);
        this.repository = repository;
    }

    public Optional<Entity> findById(Id id) {
        checkAccess();
        if (id == null) return Optional.empty();

        return repository.findById(id);
    }

    public SearchResult<Entity> findWithFilters(Filters filters) {
        checkAccess();

        return repository.find(filters);
    }

}
