package com.rcore.domain.role.usecase;

import com.rcore.domain.role.entity.Role;
import com.rcore.domain.user.entity.UserEntity;

import java.util.Set;

public class CheckAccessByRoleUseCase {

    public Boolean checkAccessByRoles(UserEntity userEntity, Set<Role> availableRoles){
        for(Role roleEntity : userEntity.getRoles()){
            for(Role role : availableRoles){
                if(roleEntity.getId().equals(role.getId())) return true;
            }
        }

        return false;
    }

}
