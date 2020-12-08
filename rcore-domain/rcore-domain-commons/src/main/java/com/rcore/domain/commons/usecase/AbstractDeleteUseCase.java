package com.rcore.domain.commons.usecase;

import com.rcore.domain.commons.port.DeleteRepository;
import lombok.Value;

/**
 * Абстрактный класс для удаляющего use case
 * @param <Id> - Тип идентификатора сущности
 * @param <Repository> - удаляющий репозиторий
 */
public abstract class AbstractDeleteUseCase<Id, Repository extends DeleteRepository<Id>> extends UseCase<AbstractDeleteUseCase.InputValues<Id>, AbstractDeleteUseCase.OutputValues> {

    protected final Repository repository;

    public AbstractDeleteUseCase(Repository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(InputValues<Id> idInputValues) {
        return OutputValues.of(repository.delete(idInputValues.getId()));
    }

    @Value(staticConstructor = "of")
    public static class InputValues<Id> implements UseCase.InputValues {
        private final Id id;
    }

    @Value(staticConstructor = "of")
    public static class OutputValues implements UseCase.OutputValues {
        private final Boolean hasBeenDeleted;
    }

}
