package com.rcore.domain.base.port;

import com.rcore.domain.base.entity.BaseEntity;

import java.util.Optional;

public interface CRUDRepository<Id, Entity extends BaseEntity> {

    Entity save(Entity entity);

    Boolean delete(Entity entity);

    Boolean deleteById(Id id);

    Optional<Entity> findById(Id id);

    SearchResult<Entity> find(SearchRequest request);

    Long count();

}
