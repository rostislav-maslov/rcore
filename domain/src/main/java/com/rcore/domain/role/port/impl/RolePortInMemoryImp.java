package com.rcore.domain.role.port.impl;

import com.rcore.domain.role.entity.Role;
import com.rcore.domain.role.port.RolePort;
import com.rcore.domain.role.port.impl.javaclassfinder.JavaClassFinder;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RolePortInMemoryImp implements RolePort {

    protected static final String BASE_PACKAGE = "*";

    protected final Set<Role> roles;

    public RolePortInMemoryImp() {
        Set<Role> roles = Collections.emptySet();
        roles.addAll(findAllRoleClassesByClassPath());
        this.roles = roles;
    }

    @Override
    public Set<Role> getAllRoles() {
        return roles;
    }

    @Override
    public Role getRole(String id) {
        for (Role role : roles) {
            if (role.getId().equals(id)) return role;
        }
        return null;
    }


    protected Set<Role> findAllRoleClassesByClassPath() {
        try {
            JavaClassFinder classFinder = new JavaClassFinder();
            List<Class<? extends Role>> classes = classFinder.findAllMatchingTypes(Role.class);

            Set<Role> roles = new HashSet<>();
            for (Class<? extends Role> roleClass : classes) {
                roles.add(Role.getInstance(roleClass));
            }
            return roles;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
