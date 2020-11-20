package com.rcore.domain.database.memory.user.port;

import com.rcore.domain.base.port.SearchRequest;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.user.entity.UserEntity;
import com.rcore.domain.user.port.UserRepository;
import com.rcore.domain.user.port.filters.UserFilters;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private Map<String, UserEntity> container = new HashMap<>();

    @Override
    public Long count() {
        return null;
    }

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
    public Optional<UserEntity> findById(String id) {
        return Optional.ofNullable(container.get(id));
    }

    @Override
    public SearchResult<UserEntity> find(SearchRequest request) {
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

    @Override
    public SearchResult<UserEntity> findWithFilters(UserFilters userFilters) {
        return null;
    }
}
