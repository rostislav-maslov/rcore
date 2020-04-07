package com.rcore.domain.user.port;

import com.rcore.domain.base.port.CRUDRepository;
import com.rcore.domain.user.entity.UserEntity;

import java.util.Optional;

public interface UserRepository<Entity extends UserEntity> extends CRUDRepository<String, Entity> {
    Optional<Entity> findByEmail(String email);
    Optional<Entity> findByPhoneNumber(Long phoneNumber);
    Optional<Entity> findByLogin(String login);
}
