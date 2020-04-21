package com.rcore.domain.access.port;

import com.rcore.domain.access.entity.Access;

import java.util.Set;

public interface AccessPort {
    Set<Access> getAllRoles();
    Access getRole(String id);
}
