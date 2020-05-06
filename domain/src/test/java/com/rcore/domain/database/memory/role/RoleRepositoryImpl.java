package com.rcore.domain.database.memory.role;

import com.rcore.domain.base.port.SearchRequest;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.role.entity.RoleEntity;
import com.rcore.domain.role.port.RoleRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class RoleRepositoryImpl implements RoleRepository {

    private Map<String, RoleEntity> container = new HashMap<>();

    @Override
    public Optional<RoleEntity> findByName(String name) {
        return container.values()
                .stream()
                .filter(role -> role.getName().equals(name))
                .findFirst();
    }

    @Override
    public RoleEntity save(RoleEntity object) {
        return container.put(object.getId(), object);
    }

    @Override
    public Boolean delete(RoleEntity object) {
        container.remove(object.getId());
        return true;
    }

    @Override
    public Boolean deleteById(String id) {
        container.remove(id);
        return true;
    }

    @Override
    public Optional<RoleEntity> findById(String id) {
        return container.values()
                .stream()
                .filter(role -> role.getId().equals(id))
                .findFirst();
    }

    @Override
    public SearchResult<RoleEntity> find(SearchRequest request) {
        return null;
    }

    @Override
    public Long count() {
        return null;
    }
}
