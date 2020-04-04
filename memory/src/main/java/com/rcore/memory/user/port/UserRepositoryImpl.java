package com.rcore.memory.user.port;

import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.memory.base.port.CRUDRepositoryImpl;

public class UserRepositoryImpl extends CRUDRepositoryImpl<String, UserEntity> implements UserRepository {

    @Override
    public UserEntity save(UserEntity object) {
        container.put(object.getId(), object);
        return object;
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
    public UserEntity findById(String id) {
        return container.get(id);
    }

    @Override
    public UserEntity findByEmail(String email) {
        for (UserEntity userEntity : this.container.values()) {
            if (userEntity.getEmail().equals(email)) return userEntity;
        }

        return null;
    }

    @Override
    public UserEntity findByPhoneNumber(Long phoneNumber) {
        for (UserEntity userEntity : this.container.values()) {
            if (userEntity.getPhoneNumber().equals(phoneNumber)) return userEntity;
        }

        return null;
    }

    @Override
    public UserEntity findByLogin(String login) {
        for (UserEntity userEntity : this.container.values()) {
            if (userEntity.getLogin().equals(login)) return userEntity;
        }

        return null;
    }
}
