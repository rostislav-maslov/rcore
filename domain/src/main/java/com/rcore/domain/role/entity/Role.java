package com.rcore.domain.role.entity;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public abstract class Role {
    public static Set<Role> concat(Set<Role> roles, Role role){
        roles.add(role);
        return roles;
    }

    public static Set<Role> concat(Set<Role> roles, Set<Role> rolesArr){
        roles.addAll(rolesArr);
        return roles;
    }

    public static Set<Role> concat(Role role, Role role2){
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        roles.add(role2);
        return roles;
    }

    public static Boolean hasAccess(Set<Role> userRoles, Role needRole ){
        if(userRoles.contains(new GodModRole())) return true;
        if(userRoles.contains(needRole)) return true;

        return false;
    }

    protected String id = this.getClass().getName();
    protected String roleTitle = "";
    protected String roleTitleKey = "";
    protected String roleDescription = "";
    protected String roleDescriptionKey = "";

    public static Role getInstance(Class<? extends Role> roleClass) throws IllegalAccessException, InvocationTargetException, InstantiationException {
        return (Role) roleClass.getDeclaredConstructors()[0].newInstance();
    }

    public Role() {
    }

    @Override
    public int hashCode() {
        return this.getId().hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || !(o instanceof Role)) return false;

        Role that = (Role) o;

        return this.id.equals(that.getId());
    }


}
