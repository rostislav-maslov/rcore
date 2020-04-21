package com.rcore.domain.base.roles;

import com.rcore.domain.access.entity.GodModAccess;
import com.rcore.domain.role.entity.RoleEntity;
import lombok.Getter;

@Getter
public class SuperUserRole extends RoleEntity {

    public SuperUserRole() {
        this.accesses.add(new GodModAccess());
        this.name = BaseRoles.SUPER_USER;
        this.locale = "Суперпользователь";
    }
}
