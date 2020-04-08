package com.rcore.domain.userPasswordRecover.usecase.admin;

import com.rcore.domain.userPasswordRecover.entity.UserPasswordRecoverEntity;
import com.rcore.domain.userPasswordRecover.port.UserPasswordRecoverIdGenerator;
import com.rcore.domain.userPasswordRecover.port.UserPasswordRecoverRepository;
import com.rcore.domain.userPasswordRecover.role.AdminUserPasswordRecoverCreateRole;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;


public class UserPasswordRecoverCreateUseCase  extends UserPasswordRecoverAdminBaseUseCase {
    private final UserPasswordRecoverIdGenerator idGenerator;

    public UserPasswordRecoverCreateUseCase(UserEntity actor, UserPasswordRecoverRepository userPasswordRecoverRepository, UserPasswordRecoverIdGenerator idGenerator) throws AuthorizationException {
        super(actor, userPasswordRecoverRepository, new AdminUserPasswordRecoverCreateRole());
        this.idGenerator = idGenerator;
    }

    public UserPasswordRecoverEntity create(UserPasswordRecoverEntity userPasswordRecoverEntity) {
        userPasswordRecoverEntity.setId(idGenerator.generate());

        userPasswordRecoverEntity = userPasswordRecoverRepository.save(userPasswordRecoverEntity);

        return userPasswordRecoverEntity;
    }


}