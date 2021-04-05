package com.rcore.domain.auth.role.port;

import com.rcore.domain.auth.role.entity.RoleEntity;
import com.rcore.domain.auth.role.port.filters.RoleFilters;
import com.rcore.domain.commons.port.CRUDRepository;

import java.util.Optional;

public interface RoleRepository extends CRUDRepository<String, RoleEntity, RoleFilters> {
    Optional<RoleEntity> findByName(String name);
}
