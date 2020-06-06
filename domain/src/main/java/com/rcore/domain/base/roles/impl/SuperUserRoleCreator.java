package com.rcore.domain.base.roles.impl;

import com.rcore.domain.access.entity.Access;
import com.rcore.domain.access.entity.GodModAccess;
import com.rcore.domain.base.roles.BaseRoles;
import com.rcore.domain.base.roles.RoleCreator;
import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.role.port.RoleIdGenerator;
import com.rcore.domain.role.port.RoleRepository;

import java.util.HashSet;
import java.util.Set;

public class SuperUserRoleCreator implements RoleCreator {

    public static final Set<Access> accesses = new HashSet<>();

    private final RoleRepository roleRepository;
    private final RoleIdGenerator roleIdGenerator;

    public SuperUserRoleCreator(RoleRepository roleRepository, RoleIdGenerator roleIdGenerator) {
        this.roleRepository = roleRepository;
        this.roleIdGenerator = roleIdGenerator;

        accesses.add(new GodModAccess());
    }

    @Override
    public RoleEntity create() {
        return roleRepository.save(RoleEntity.builder()
                .id(roleIdGenerator.generate())
                .name(BaseRoles.SUPER_USER)
                .locale("Суперпользователь")
                .accesses(accesses)
                .build());
    }
}
