package com.ub.core.base.role;

public class BaseAdminRole extends Role{
    public BaseAdminRole(){
        this.id = this.getClass().getName();
        this.roleDescription = "Базовая роль для доступа к админке";
        this.roleTitle = "Базовая роль для доступа к админке";
    }
}
