package com.rcore.domain.base.roles;

import com.rcore.domain.access.entity.Access;
import com.rcore.domain.role.entity.RoleEntity;

import java.util.HashSet;
import java.util.Set;

public interface RoleCreator {

    Set<Access> accesses = new HashSet<>();

    RoleEntity create();

}
