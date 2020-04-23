package com.rcore.domain.userPasswordRecover.usecase.admin;

import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.userPasswordRecover.entity.UserPasswordRecoverEntity;
import com.rcore.domain.userPasswordRecover.port.UserPasswordRecoverRepository;
import com.rcore.domain.userPasswordRecover.access.AdminUserPasswordRecoverViewAccess;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.base.port.SearchResult;

public class UserPasswordRecoverViewUseCase extends UserPasswordRecoverAdminBaseUseCase {

    public UserPasswordRecoverViewUseCase(UserPasswordRecoverRepository userPasswordRecoverRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase) {
        super(userPasswordRecoverRepository, new AdminUserPasswordRecoverViewAccess(), authorizationByTokenUseCase);
    }

    public UserPasswordRecoverEntity findById(String id) throws AuthenticationException, AuthorizationException {
        checkAccess();
        return userPasswordRecoverRepository.findById(id).get();
    }

    public UserPasswordRecoverEntity search(String id) throws AuthenticationException, AuthorizationException {
        checkAccess();
        return userPasswordRecoverRepository.findById(id).get();
    }

    public SearchResult<UserPasswordRecoverEntity> find(Long size, Long skip) throws AuthenticationException, AuthorizationException {
        checkAccess();
        return userPasswordRecoverRepository.find(size, skip);
    }

}