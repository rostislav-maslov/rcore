package com.rcore.domain.access.entity;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Access {
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
        if(userAccesses.contains(new GodModAccess())) return true;
        if(userAccesses.contains(needAccess)) return true;

        return false;
    }

    protected String id = this.getClass().getSimpleName();
    protected String title = "";
    protected String description = "";

    public static Access getInstance(Class<? extends Access> roleClass) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return (Access) roleClass.getDeclaredConstructors()[0].newInstance();
    }

    public Access() {
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Access)) return false;

        Access that = (Access) o;

        return this.id.equals(that.getId());
    }


}
