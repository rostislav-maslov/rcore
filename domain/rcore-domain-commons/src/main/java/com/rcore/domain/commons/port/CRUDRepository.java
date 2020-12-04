package com.rcore.domain.commons.port;

import com.rcore.domain.commons.entity.BaseEntity;

import java.util.Optional;

public interface CRUDRepository<Id, Entity extends BaseEntity, Filters extends SearchFilters>
        extends DeleteRepository<Id>, CreateUpdateRepository<Entity>, ReadRepository<Id, Entity, Filters> {

}
