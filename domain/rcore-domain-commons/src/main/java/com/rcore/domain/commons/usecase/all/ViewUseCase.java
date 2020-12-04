package com.rcore.domain.commons.usecase.all;

import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.commons.port.ReadRepository;
import com.rcore.domain.commons.port.SearchFilters;
import com.rcore.domain.commons.port.SearchResult;
import com.rcore.domain.commons.usecase.IViewUseCase;

import java.util.Optional;

public abstract class ViewUseCase<Id, Entity extends BaseEntity, Filters extends SearchFilters, Repository extends ReadRepository<Id, Entity, Filters>>
        implements IViewUseCase<Id, Entity, Filters> {

    protected final Repository repository;

    public ViewUseCase(Repository repository) {
        this.repository = repository;
    }

    public Optional<Entity> findById(Id id) {
        if (id == null) return Optional.empty();

        return repository.findById(id);
    }

    public SearchResult<Entity> findWithFilters(Filters filters) {
        return repository.find(filters);
    }

}
