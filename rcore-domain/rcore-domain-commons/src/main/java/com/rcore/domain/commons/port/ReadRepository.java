package com.rcore.domain.commons.port;

import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.commons.port.dto.SearchFilters;
import com.rcore.domain.commons.port.dto.SearchResult;

import java.util.Optional;

public interface ReadRepository<Id, Entity extends BaseEntity<Id>, Filters extends SearchFilters> {
    Optional<Entity> findById(Id id);

    SearchResult<Entity> find(Filters filters);

    boolean exist(Id id);

    Long count();
}
