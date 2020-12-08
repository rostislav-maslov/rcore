package com.rcore.domain.commons.usecase;

import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.commons.port.ReadRepository;
import lombok.Value;

import java.util.Optional;

/**
 * Абстрактный класс для создающего use case
 * @param <Id> - Класс идентификатора сущности
 * @param <Entity> - Класс создаваемой сущности
 * @param <Repository> - репозиторий, осуществляющий поиск
 */
public abstract class AbstractFindByIdUseCase<Id, Entity extends BaseEntity, Repository extends ReadRepository>
        extends UseCase<AbstractFindByIdUseCase.InputValues<Id>, AbstractFindByIdUseCase.OutputValues<Entity>>  {

    protected final Repository repository;

    public AbstractFindByIdUseCase(Repository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues<Entity> execute(InputValues<Id> idInputValues) {
        if (idInputValues.getId() == null)
            return OutputValues.empty();
        return new OutputValues<>(repository.findById(idInputValues.getId()));
    }

    @Value(staticConstructor = "of")
    public static class InputValues<Id> implements UseCase.InputValues {
        private final Id id;
    }

    @Value(staticConstructor = "of")
    public static class OutputValues<Entity extends BaseEntity> implements UseCase.OutputValues {
        private final Optional<Entity> result;

        public static OutputValues empty() {
            return new OutputValues(Optional.empty());
        }
    }
}
