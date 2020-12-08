package com.rcore.domain.commons.usecase;

import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.commons.port.ReadRepository;
import com.rcore.domain.commons.port.dto.SearchFilters;
import com.rcore.domain.commons.port.dto.SearchResult;
import lombok.Value;

/**
 * Абстрактный класс для создающего use case
 * @param <Entity> - Класс создаваемой сущности
 * @param <Repository> - репозиторий чтения
 * @param <Filters> - фильтры
 */
public abstract class AbstractFindWithFiltersUseCase<Entity extends BaseEntity, Filters extends SearchFilters, Repository extends ReadRepository>
        extends UseCase<AbstractFindWithFiltersUseCase.InputValues<Filters>, AbstractFindWithFiltersUseCase.OutputValues<Entity>> {

    protected final Repository repository;

    public AbstractFindWithFiltersUseCase(Repository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValues<Entity> execute(InputValues<Filters> inputValues) {
        return new OutputValues<>(repository.find(inputValues.getFilters()));
    }

    @Value
    public static  class InputValues<Filters> implements UseCase.InputValues {
        private final Filters filters;
    }

    @Value
    public static class OutputValues<Entity extends BaseEntity> implements UseCase.OutputValues {
        private final SearchResult<Entity> result;
    }

}
