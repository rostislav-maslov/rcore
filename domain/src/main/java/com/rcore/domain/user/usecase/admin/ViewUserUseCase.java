package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.access.AdminUserViewAccess;

import java.util.Optional;

public class ViewUserUseCase extends AdminBaseUseCase {

    public ViewUserUseCase(UserEntity actor, UserRepository userRepository) throws AuthorizationException {
        super(actor, userRepository, new AdminUserViewAccess());
    }

    public Optional<UserEntity> findById(String id) {
        return userRepository.findById(id);
    }

    public SearchResult<UserEntity> find(Long size, Long skip) {
        return userRepository.find(size, skip);
    }

}