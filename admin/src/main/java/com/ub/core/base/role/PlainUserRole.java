package com.ub.core.base.role;

public class PlainUserRole extends Role{
    public PlainUserRole(){
        this.id = this.getClass().getName();
        this.roleDescription = "Простой пользователь";
        this.roleTitle = "Простой пользователь";
    }
}
