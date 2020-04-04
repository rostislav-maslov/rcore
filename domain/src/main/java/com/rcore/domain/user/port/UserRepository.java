package com.rcore.domain.user.port;

import com.rcore.domain.base.port.CRUDRepository;
import com.rcore.domain.user.entity.UserEntity;

public interface UserRepository extends CRUDRepository<String, UserEntity> {
    UserEntity findByEmail(String email);
    UserEntity findByPhoneNumber(Long phoneNumber);
    UserEntity findByLogin(String login);
}
