package com.rcore.domain.userEmailVerification.usecase.admin;

import com.rcore.domain.userEmailVerification.entity.UserEmailVerificationEntity;
import com.rcore.domain.userEmailVerification.port.UserEmailVerificationRepository;
import com.rcore.domain.userEmailVerification.role.AdminUserEmailVerificationViewRole;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.base.port.SearchResult;

import java.util.Optional;

public class UserEmailVerificationViewUseCase extends UserEmailVerificationAdminBaseUseCase {

    public UserEmailVerificationViewUseCase(UserEntity actor, UserEmailVerificationRepository userEmailVerificationRepository) throws AuthorizationException {
        super(actor, userEmailVerificationRepository, new AdminUserEmailVerificationViewRole());
    }

    public Optional<UserEmailVerificationEntity> findById(String id) {
        return userEmailVerificationRepository.findById(id);
    }

    public Optional<UserEmailVerificationEntity> search(String id) {
        return userEmailVerificationRepository.findById(id);
    }

    public SearchResult<UserEmailVerificationEntity> find(Long size, Long skip) {
        return userEmailVerificationRepository.find(size, skip);
    }

}