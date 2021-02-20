package com.rcore.domain.commons.port;

import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.commons.port.dto.SearchFilters;

public interface CRUDRepository<Id, Entity extends BaseEntity, Filters extends SearchFilters>
        extends DeleteRepository<Id>, CreateUpdateRepository<Entity>, ReadRepository<Id, Entity, Filters> {

}
