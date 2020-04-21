package com.rcore.domain.access.usecase;

import com.rcore.domain.access.entity.Access;
import com.rcore.domain.user.entity.UserEntity;

import java.util.Set;

public class CheckAccessByRoleUseCase {

    public Boolean checkAccessByRoles(UserEntity userEntity, Set<Access> availableAccesses){
        for(Access accessEntity : userEntity.getAccesses()){
            for(Access access : availableAccesses){
                if(accessEntity.getId().equals(access.getId())) return true;
            }
        }

        return false;
    }

}
