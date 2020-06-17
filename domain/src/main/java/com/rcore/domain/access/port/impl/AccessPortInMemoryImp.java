package com.rcore.domain.access.port.impl;

import com.rcore.domain.access.entity.Access;
import com.rcore.domain.access.port.AccessPort;
import com.rcore.domain.access.port.impl.javaclassfinder.JavaClassFinder;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AccessPortInMemoryImp implements AccessPort {

    protected static final String BASE_PACKAGE = "*";

    protected final Set<Access> accesses;

    public AccessPortInMemoryImp() {
        Set<Access> accesses = Collections.emptySet();
//        accesses.addAll(findAllRoleClassesByClassPath());
        this.accesses = accesses;
    }

    @Override
    public Set<Access> getAllRoles() {
        return accesses;
    }

    @Override
    public Access getRole(String id) {
        for (Access access : accesses) {
            if (access.getId().equals(id)) return access;
        }
        return null;
    }


    protected Set<Access> findAllRoleClassesByClassPath() {
        try {
            JavaClassFinder classFinder = new JavaClassFinder();
            List<Class<? extends Access>> classes = classFinder.findAllMatchingTypes(Access.class);

            Set<Access> accesses = new HashSet<>();
            for (Class<? extends Access> roleClass : classes) {
//                accesses.add(Access.getInstance(roleClass));
            }
            return accesses;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
