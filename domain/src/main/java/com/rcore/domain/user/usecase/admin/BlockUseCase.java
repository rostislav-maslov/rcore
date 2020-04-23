package com.rcore.domain.user.usecase.admin;

import com.rcore.domain.token.exception.AuthenticationException;
import com.rcore.domain.token.exception.AuthorizationException;
import com.rcore.domain.token.usecase.AuthorizationByTokenUseCase;
import com.rcore.domain.token.usecase.ExpireTokenUseCase;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.entity.UserStatus;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.access.AdminUserBlockAccess;

public class BlockUseCase extends AdminBaseUseCase {

    private final ExpireTokenUseCase expireTokenUseCase;

    public BlockUseCase(UserRepository userRepository, ExpireTokenUseCase expireTokenUseCase, AuthorizationByTokenUseCase authorizationByTokenUseCase) {
        super(userRepository, new AdminUserBlockAccess(), authorizationByTokenUseCase);
        this.expireTokenUseCase = expireTokenUseCase;
    }


    public UserEntity block(UserEntity userEntity) throws AuthorizationException, AuthenticationException {
        checkAccess();

        userEntity.setStatus(UserStatus.BLOCK);
        userEntity.setFails(0);
        userEntity = userRepository.save(userEntity);

        expireTokenUseCase.logout(userEntity);

        return userEntity;
    }
}
