package com.rcore.domain.commons.usecase;

import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.commons.port.ReadRepository;
import com.rcore.domain.commons.usecase.model.IdInputValues;
import com.rcore.domain.commons.usecase.model.SingletonOptionalEntityOutputValues;
import lombok.Value;

import java.util.Optional;

/**
 * Абстрактный класс для создающего use case
 * @param <Id> - Класс идентификатора сущности
 * @param <Entity> - Класс создаваемой сущности
 * @param <Repository> - репозиторий, осуществляющий поиск
 */
public abstract class AbstractFindByIdUseCase<Id, Entity extends BaseEntity, Repository extends ReadRepository>
        extends UseCase<IdInputValues<Id>, SingletonOptionalEntityOutputValues<Entity>>  {

    protected final Repository repository;

    public AbstractFindByIdUseCase(Repository repository) {
        this.repository = repository;
    }

    @Override
    public SingletonOptionalEntityOutputValues<Entity> execute(IdInputValues<Id> idInputValues) {
        if (idInputValues.getId() == null)
            return SingletonOptionalEntityOutputValues.empty();

        return SingletonOptionalEntityOutputValues.of(repository.findById(idInputValues.getId()));
    }
}
