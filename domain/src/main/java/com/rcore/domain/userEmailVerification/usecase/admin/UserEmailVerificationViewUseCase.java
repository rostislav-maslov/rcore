package com.rcore.domain.userEmailVerification.usecase.admin;

import com.rcore.domain.base.port.SearchRequest;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.exception.TokenExpiredException;
import com.rcore.domain.userEmailVerification.entity.UserEmailVerificationEntity;
import com.rcore.domain.userEmailVerification.port.UserEmailVerificationRepository;
import com.rcore.domain.userEmailVerification.access.AdminUserEmailVerificationViewAccess;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.base.port.SearchResult;

import java.util.Optional;

public class UserEmailVerificationViewUseCase extends UserEmailVerificationAdminBaseUseCase {

    public UserEmailVerificationViewUseCase(UserEmailVerificationRepository userEmailVerificationRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase) throws AuthorizationException {
        super(userEmailVerificationRepository, new AdminUserEmailVerificationViewAccess(), authorizationByTokenUseCase);
    }

    public Optional<UserEmailVerificationEntity> findById(String id) throws AuthenticationException, AuthorizationException, TokenExpiredException {
        checkAccess();
        return userEmailVerificationRepository.findById(id);
    }

    public Optional<UserEmailVerificationEntity> search(String id) throws AuthenticationException, AuthorizationException, TokenExpiredException {
        checkAccess();
        return userEmailVerificationRepository.findById(id);
    }

    public SearchResult<UserEmailVerificationEntity> find(SearchRequest request) throws AuthenticationException, AuthorizationException, TokenExpiredException {
        checkAccess();
        return userEmailVerificationRepository.find(request);
    }

}