package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.access.entity.Access;
import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.user.access.AdminDeleteUserProfileImageAccess;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.exception.TokenExpiredException;
import com.rcore.domain.user.port.UserRepository;

import java.time.LocalDateTime;

public class DeleteCurrentUserProfileImageUseCase extends AdminBaseUseCase {

    public DeleteCurrentUserProfileImageUseCase(UserRepository userRepository, AuthorizationByTokenUseCase authorizationByTokenUseCase) {
        super(userRepository, new AdminDeleteUserProfileImageAccess(), authorizationByTokenUseCase);
    }

    public UserEntity deleteProfileImage() throws AuthorizationException, TokenExpiredException, AuthenticationException {
        UserEntity userEntity = checkAccess();

        userEntity.setProfileImageId(null);
        userEntity.setUpdatedAt(LocalDateTime.now());
        userEntity = userRepository.save(userEntity);
        return userEntity;
    }
}
