package com.rcore.domain.commons.usecase;

import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.commons.port.ReadRepository;
import com.rcore.domain.commons.port.dto.SearchFilters;
import com.rcore.domain.commons.usecase.model.FiltersInputValues;
import com.rcore.domain.commons.usecase.model.SearchResultEntityOutputValues;

/**
 * Абстрактный класс для создающего use case
 * @param <Entity> - Класс создаваемой сущности
 * @param <Repository> - репозиторий чтения
 * @param <Filters> - фильтры
 */
public abstract class AbstractFindWithFiltersUseCase<Entity extends BaseEntity, Filters extends SearchFilters, Repository extends ReadRepository>
        extends UseCase<FiltersInputValues<Filters>, SearchResultEntityOutputValues<Entity>> {

    protected final Repository repository;

    public AbstractFindWithFiltersUseCase(Repository repository) {
        this.repository = repository;
    }

    @Override
    public SearchResultEntityOutputValues<Entity> execute(FiltersInputValues<Filters> inputValues) {
        return SearchResultEntityOutputValues.of(repository.find(inputValues.getFilters()));
    }


}
