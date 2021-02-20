package com.rcore.domain.commons.usecase;

import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.commons.port.ReadRepository;
import com.rcore.domain.commons.port.dto.SearchFilters;
import com.rcore.domain.commons.usecase.model.FiltersInputValues;
import com.rcore.domain.commons.usecase.model.SearchResultEntityOutputValues;
import com.rcore.domain.commons.usecase.model.VoidInputValues;

/**
 * Абстрактный класс для создающего use case
 * @param <Entity> - Класс создаваемой сущности
 * @param <Repository> - репозиторий чтения
 * @param <Filters> - фильтры
 */
public abstract class AbstractFindAllUseCase<Entity extends BaseEntity, Filters extends SearchFilters, Repository extends ReadRepository>
        extends UseCase<VoidInputValues, SearchResultEntityOutputValues<Entity>> {

    protected final Repository repository;

    public AbstractFindAllUseCase(Repository repository) {
        this.repository = repository;
    }

    @Override
    public SearchResultEntityOutputValues<Entity> execute(VoidInputValues voidInputValues) {
        return null;
    }

    public static class Inpu implements UseCase.InputValues {

    }

    public static class Ou implements UseCase.OutputValues {

    }


}
