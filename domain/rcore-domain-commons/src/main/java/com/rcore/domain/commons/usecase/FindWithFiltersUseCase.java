package com.rcore.domain.commons.usecase;

import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.commons.port.ReadRepository;
import com.rcore.domain.commons.port.SearchFilters;
import com.rcore.domain.commons.port.SearchResult;
import lombok.Value;

public abstract class FindWithFiltersUseCase<Id, Entity extends BaseEntity, Filters extends SearchFilters, Repository extends ReadRepository<Id, Entity, Filters>>
        extends UseCase<FindWithFiltersUseCase.InputValues<Filters>, FindWithFiltersUseCase.OutputValues<Entity>> {

    private final Repository repository;

    public FindWithFiltersUseCase(Repository repository) {
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
