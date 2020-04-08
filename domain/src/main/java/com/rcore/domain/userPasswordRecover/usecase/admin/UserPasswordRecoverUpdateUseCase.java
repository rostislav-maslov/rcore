package com.rcore.domain.userPasswordRecover.usecase.admin;

import com.rcore.domain.userPasswordRecover.entity.UserPasswordRecoverEntity;
import com.rcore.domain.userPasswordRecover.port.UserPasswordRecoverRepository;
import com.rcore.domain.userPasswordRecover.role.AdminUserPasswordRecoverUpdateRole;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;

public class UserPasswordRecoverUpdateUseCase  extends UserPasswordRecoverAdminBaseUseCase {

    public UserPasswordRecoverUpdateUseCase(UserEntity actor, UserPasswordRecoverRepository userPasswordRecoverRepository)throws AuthorizationException {
        super(actor, userPasswordRecoverRepository, new AdminUserPasswordRecoverUpdateRole());
    }

    public UserPasswordRecoverEntity update(UserPasswordRecoverEntity userPasswordRecoverEntity){
        userPasswordRecoverEntity.setUpdatedAt(new Date());
        return userPasswordRecoverRepository.save(userPasswordRecoverEntity);
    }


}