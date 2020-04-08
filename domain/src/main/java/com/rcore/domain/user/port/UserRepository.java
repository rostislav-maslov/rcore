package com.rcore.domain.user.port;

import com.rcore.domain.base.port.CRUDRepository;
import com.rcore.domain.user.entity.UserEntity;

import java.util.Optional;

public abstract class UserRepository<Entity extends UserEntity> extends CRUDRepository<String, Entity> {
    public abstract Optional<Entity> findByEmail(String email);
    public abstract Optional<Entity> findByPhoneNumber(Long phoneNumber);
    public abstract Optional<Entity> findByLogin(String login);
}
