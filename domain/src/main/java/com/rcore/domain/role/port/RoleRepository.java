package com.rcore.domain.role.port;

import com.rcore.domain.base.port.CRUDRepository;
import com.rcore.domain.role.entity.RoleEntity;

import java.util.Optional;

public interface RoleRepository extends CRUDRepository<String, RoleEntity> {
    Optional<RoleEntity> findByName(String name);
}
