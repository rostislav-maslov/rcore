package com.rcore.domain.security.usecase.secured;

import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.commons.port.BaseIdGenerator;
import com.rcore.domain.commons.port.CreateUpdateRepository;
import com.rcore.domain.commons.usecase.ICreateUseCase;
import com.rcore.domain.security.port.CredentialVerifier;

public abstract class SecuredCreateUseCase<Id, IdGenerator extends BaseIdGenerator<Id>, Entity extends BaseEntity, Repository extends CreateUpdateRepository<Entity>, Command>
        extends SecuredUseCase implements ICreateUseCase<Entity, Command> {

    protected final Repository repository;
    protected final IdGenerator idGenerator;

    public SecuredCreateUseCase(CredentialVerifier credentialVerifier, Repository repository, IdGenerator idGenerator) {
        super(credentialVerifier);
        this.repository = repository;
        this.idGenerator = idGenerator;
    }
}
