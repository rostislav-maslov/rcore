package com.rcore.domain.role.port;

import com.rcore.domain.role.entity.Role;

import java.util.Set;

public interface RolePort {
    Set<Role> getAllRoles();
    Role getRole(String id);
}
