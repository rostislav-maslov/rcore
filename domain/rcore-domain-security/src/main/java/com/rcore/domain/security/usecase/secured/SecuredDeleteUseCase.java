package com.rcore.domain.security.usecase.secured;

import com.rcore.domain.commons.port.DeleteRepository;
import com.rcore.domain.commons.usecase.IDeleteUseCase;
import com.rcore.domain.security.port.CredentialVerifier;

public abstract class SecuredDeleteUseCase<Id, Repository extends DeleteRepository<Id>>
        extends SecuredUseCase implements IDeleteUseCase<Id> {

    protected final Repository repository;

    public SecuredDeleteUseCase(CredentialVerifier credentialVerifier, Repository repository) {
        super(credentialVerifier);
        this.repository = repository;
    }

    @Override
    public Boolean delete(Id id) {
        checkAccess();
        return repository.delete(id);
    }
}
