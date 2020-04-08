package com.rcore.domain.userPasswordRecover.usecase.admin;

import com.rcore.domain.userPasswordRecover.entity.UserPasswordRecoverEntity;
import com.rcore.domain.userPasswordRecover.port.UserPasswordRecoverRepository;
import com.rcore.domain.userPasswordRecover.role.AdminUserPasswordRecoverDeleteRole;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;

public class UserPasswordRecoverDeleteUseCase  extends UserPasswordRecoverAdminBaseUseCase {

    public UserPasswordRecoverDeleteUseCase(UserEntity actor, UserPasswordRecoverRepository userPasswordRecoverRepository) throws AuthorizationException {
        super(actor, userPasswordRecoverRepository, new AdminUserPasswordRecoverDeleteRole());
    }

    public Boolean delete(UserPasswordRecoverEntity userPasswordRecoverEntity){
        userPasswordRecoverRepository.delete(userPasswordRecoverEntity);

        return true;
    }


}