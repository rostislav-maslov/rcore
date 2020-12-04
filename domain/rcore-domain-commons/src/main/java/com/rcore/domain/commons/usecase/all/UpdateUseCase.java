package com.rcore.domain.commons.usecase.all;

import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.commons.port.CreateUpdateRepository;
import com.rcore.domain.commons.usecase.IUpdateUseCase;

public abstract class UpdateUseCase<Id, Entity extends BaseEntity, Repository extends CreateUpdateRepository<Entity>, Command>
        implements IUpdateUseCase<Entity, Command> {

    protected final Repository repository;

    public UpdateUseCase(Repository repository) {
        this.repository = repository;
    }
}
