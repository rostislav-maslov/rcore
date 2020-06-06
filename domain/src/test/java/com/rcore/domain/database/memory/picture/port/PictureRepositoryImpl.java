package com.rcore.domain.database.memory.picture.port;

import com.rcore.domain.base.port.SearchRequest;
import com.rcore.domain.base.port.SearchResult;
import com.rcore.domain.picture.entity.PictureEntity;
import com.rcore.domain.picture.port.PictureRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PictureRepositoryImpl implements PictureRepository {

    private Map<String, PictureEntity> container = new HashMap<>();

    @Override
    public PictureEntity save(PictureEntity object) {
        container.put(object.getId(), object);
        return object;
    }

    @Override
    public Boolean delete(PictureEntity object) {
        container.remove(object.getId());
        return true;
    }

    @Override
    public Boolean deleteById(String id) {
        container.remove(id);
        return true;
    }

    @Override
    public Optional<PictureEntity> findById(String id) {
        return Optional.ofNullable(container.get(id));
    }

    @Override
    public SearchResult<PictureEntity> find(SearchRequest request) {
        return null;
    }

    @Override
    public Long count() {
        return (long) container.size();
    }
}
