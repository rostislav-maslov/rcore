package com.rcore.database.memory.user.port;

import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.database.memory.base.port.CRUDRepositoryImpl;

import java.util.Optional;

public class UserRepositoryImpl extends CRUDRepositoryImpl<String, UserEntity> implements UserRepository<UserEntity> {

    @Override
    public Optional<UserEntity> save(UserEntity object) {
        container.put(object.getId(), object);
        return Optional.ofNullable(object);
    }

    @Override
    public Boolean delete(UserEntity object) {
        container.remove(object.getId());
        return true;
    }

    @Override
    public Boolean deleteById(String id) {
        container.remove(id);
        return true;
    }

    @Override
    public Optional<UserEntity> findById(String id) {
        return Optional.ofNullable(container.get(id));
    }

    @Override
    public SearchResult<UserEntity> find(Long size, Long skip) {
        return null;
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        for (UserEntity userEntity : this.container.values()) {
            if (userEntity.getEmail().equals(email)) return Optional.ofNullable(userEntity);
        }

        return Optional.empty();
    }

    @Override
    public Optional<UserEntity> findByPhoneNumber(Long phoneNumber) {
        for (UserEntity userEntity : this.container.values()) {
            if (userEntity.getPhoneNumber().equals(phoneNumber)) return Optional.ofNullable(userEntity);
        }

        return Optional.empty();
    }

    @Override
    public Optional<UserEntity> findByLogin(String login) {
        for (UserEntity userEntity : this.container.values()) {
            if (userEntity.getLogin().equals(login)) return Optional.ofNullable(userEntity);
        }

        return Optional.empty();
    }
}
