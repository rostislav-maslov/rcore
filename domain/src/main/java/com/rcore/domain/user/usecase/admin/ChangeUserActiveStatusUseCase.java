package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.access.AdminChangeUserActiveStatusAccess;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.UserNotFoundException;
import com.rcore.domain.user.port.UserRepository;

import java.time.LocalDateTime;

public class ChangeUserActiveStatusUseCase extends AdminBaseUseCase {

    public ChangeUserActiveStatusUseCase(UserRepository userRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase) {
        super(userRepository, new AdminChangeUserActiveStatusAccess(), authorizationByTokenUseCase);
    }

    public UserEntity changeActiveStatus(String id) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        userEntity.inverseStatus();
        userEntity.setUpdatedAt(LocalDateTime.now());
        userEntity = userRepository.save(userEntity);
        return userEntity;
    };
}
