package com.rcore.domain.commons.usecase;

import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.commons.exception.DomainException;
import com.rcore.domain.commons.port.ReadRepository;
import com.rcore.domain.commons.usecase.model.IdInputValues;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;

import java.util.Optional;

public abstract class AbstractFindByIdWithThrowExceptionUseCase<Id, Entity extends BaseEntity<Id>, Repository extends ReadRepository<Id, Entity, ?>>
        extends UseCase<IdInputValues<Id>, SingletonEntityOutputValues<Entity>> {

    protected final Repository repository;
    protected final DomainException exception;

    public AbstractFindByIdWithThrowExceptionUseCase(Repository repository, DomainException exception) {
        this.repository = repository;
        this.exception = exception;
    }

    @Override
    public SingletonEntityOutputValues<Entity> execute(IdInputValues<Id> idInputValues) {
        if (idInputValues.getId() == null)
            throw exception;

        Optional<Entity> entity = repository.findById(idInputValues.getId());
        if (entity.isEmpty())
            throw exception;

        return SingletonEntityOutputValues.of(entity.get());
    }
}
