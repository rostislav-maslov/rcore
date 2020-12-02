package com.rcore.domain.commons.port;

import com.rcore.domain.commons.entity.BaseEntity;

import java.util.Optional;

public interface CRUDRepository<Id, Entity extends BaseEntity, Filters extends SearchFilters> {

    Entity save(Entity entity);

    Boolean delete(Entity entity);

    Boolean deleteById(Id id);

    Optional<Entity> findById(Id id);

    SearchResult<Entity> find(Filters request);

    Long count();

}
