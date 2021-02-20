package com.rcore.domain.role.port;

import com.rcore.domain.commons.port.CRUDRepository;
import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.role.port.filters.RoleFilters;

import java.util.Optional;

public interface RoleRepository extends CRUDRepository<String, RoleEntity, RoleFilters> {
    Optional<RoleEntity> findByName(String name);
}
