package com.rcore.domain.access.utils;

import com.rcore.domain.access.entity.Access;
import com.rcore.domain.access.entity.GodModAccess;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class AccessUtils {
    public static Set<Access> concat(Set<Access> accesses, Access access){
        accesses.add(access);
        return accesses;
    }

    public static Set<Access> concat(Set<Access> accesses, Set<Access> rolesArr){
        accesses.addAll(rolesArr);
        return accesses;
    }

    public static Set<Access> concat(Access access, Access access2){
        Set<Access> accesses = new HashSet<>();
        accesses.add(access);
        accesses.add(access2);
        return accesses;
    }

    public static Boolean hasAccess(Set<Access> userAccesses, Access needAccess){
        Optional<Access> access = userAccesses.stream().filter(a -> a.getId().equals(needAccess.getId())).findFirst();
        Boolean b = userAccesses.contains(needAccess);
        if(userAccesses.contains(new GodModAccess())) return true;
        if(userAccesses.contains(needAccess))
            return true;

        return false;
    }
}
