package com.ub.core.base.role;

public class SuperUser extends Role {
    public SuperUser(){
        this.id = this.getClass().getName();
        this.roleDescription = "Супер пользователь";
        this.roleTitle = "Роль позволяет получать доступ без ограничения.";
    }
}
