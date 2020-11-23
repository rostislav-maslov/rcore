package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.access.AdminDeleteUserProfileImageAccess;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.UserNotFoundException;
import com.rcore.domain.user.port.UserRepository;

import java.time.LocalDateTime;

public class DeleteUserProfileImageUseCase extends AdminBaseUseCase {

    public DeleteUserProfileImageUseCase(UserRepository userRepository,  AuthorizationByTokenUseCase authorizationByTokenUseCase) {
        super(userRepository, new AdminDeleteUserProfileImageAccess(), authorizationByTokenUseCase);
    }

    public UserEntity deleteProfileImage(String id) throws UserNotFoundException {
        UserEntity userEntity = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        userEntity.setProfileImageId(null);
        userEntity.setUpdatedAt(LocalDateTime.now());
        userEntity = userRepository.save(userEntity);
        return userEntity;
    }
}
