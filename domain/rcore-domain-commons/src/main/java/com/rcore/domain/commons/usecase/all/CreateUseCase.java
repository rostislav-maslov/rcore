package com.rcore.domain.commons.usecase.all;

import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.commons.port.BaseIdGenerator;
import com.rcore.domain.commons.port.CreateUpdateRepository;
import com.rcore.domain.commons.usecase.ICreateUseCase;

public abstract class CreateUseCase<Id, IdGenerator extends BaseIdGenerator<Id>, Entity extends BaseEntity, Repository extends CreateUpdateRepository<Entity>, Command>
        implements ICreateUseCase<Entity, Command> {

    protected final Repository repository;
    protected final IdGenerator idGenerator;

    public CreateUseCase(Repository repository, IdGenerator idGenerator) {
        this.repository = repository;
        this.idGenerator = idGenerator;
    }
}
