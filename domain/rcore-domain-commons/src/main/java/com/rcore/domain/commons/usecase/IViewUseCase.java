package com.rcore.domain.commons.usecase;

import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.commons.port.SearchFilters;
import com.rcore.domain.commons.port.SearchResult;

import java.util.Optional;

public interface IViewUseCase<Id, Entity extends BaseEntity, Filters extends SearchFilters> {
    Optional<Entity> findById(Id id);
    SearchResult<Entity> findWithFilters(Filters filters);
}
