package com.rcore.domain.auth.authorization.port;

import com.rcore.domain.auth.authorization.entity.AuthorizationEntity;
import com.rcore.domain.commons.port.CRUDRepository;
import com.rcore.domain.commons.port.dto.SearchFilters;

public interface AuthorizationRepository extends CRUDRepository<String, AuthorizationEntity, SearchFilters> {
}
