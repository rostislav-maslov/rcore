package com.rcore.domain.security.usecase.secured;

import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.commons.port.BaseIdGenerator;
import com.rcore.domain.commons.port.CreateUpdateRepository;
import com.rcore.domain.commons.usecase.ICreateUseCase;
import com.rcore.domain.commons.usecase.IUpdateUseCase;
import com.rcore.domain.security.port.CredentialVerifier;

public abstract class SecuredUpdateUseCase<Id, Entity extends BaseEntity, Repository extends CreateUpdateRepository<Entity>, Command>
        extends SecuredUseCase implements IUpdateUseCase<Entity, Command> {

    protected final Repository repository;

    public SecuredUpdateUseCase(CredentialVerifier credentialVerifier, Repository repository) {
        super(credentialVerifier);
        this.repository = repository;
    }
}
