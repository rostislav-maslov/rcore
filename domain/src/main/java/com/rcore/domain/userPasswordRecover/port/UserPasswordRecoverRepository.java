package com.rcore.domain.userPasswordRecover.port;

import com.rcore.domain.base.port.CRUDRepository;
import com.rcore.domain.userPasswordRecover.entity.UserPasswordRecoverEntity;

import java.util.Date;
import java.util.Optional;

public interface UserPasswordRecoverRepository extends CRUDRepository<String, UserPasswordRecoverEntity> {

    /**
     * Ищем активный UserPassword:
     * - по емейлу
     * - retryLeft > 0
     * - isRecovered == false
     * - expiredAt > new Date
     * @param email
     * @return
     */
    Optional<UserPasswordRecoverEntity> findActiveByEmail(String email);

}
