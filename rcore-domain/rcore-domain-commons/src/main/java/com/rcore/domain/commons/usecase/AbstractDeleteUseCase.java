package com.rcore.domain.commons.usecase;

import com.rcore.domain.commons.port.DeleteRepository;
import com.rcore.domain.commons.usecase.model.IdInputValues;
import lombok.Value;

/**
 * Абстрактный класс для удаляющего use case
 * @param <Id> - Тип идентификатора сущности
 * @param <Repository> - удаляющий репозиторий
 */
public abstract class AbstractDeleteUseCase<Id, Repository extends DeleteRepository<Id>> extends UseCase<IdInputValues<Id>, AbstractDeleteUseCase.OutputValues> {

    protected final Repository repository;

    public AbstractDeleteUseCase(Repository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues execute(IdInputValues<Id> idInputValues) {
        return OutputValues.of(repository.delete(idInputValues.getId()));
    }

    @Value(staticConstructor = "of")
    public static class OutputValues implements UseCase.OutputValues {
        private final Boolean hasBeenDeleted;
    }

}
