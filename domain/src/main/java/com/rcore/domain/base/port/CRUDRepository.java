package com.rcore.domain.base.port;

import com.rcore.domain.base.entity.BaseEntity;

import java.util.Date;
import java.util.Optional;

public abstract class CRUDRepository<IdT, ObjectT extends BaseEntity> {

    protected abstract Optional<ObjectT> saveToRepository(ObjectT object);

    public abstract Boolean delete(ObjectT object);

    public abstract Boolean deleteById(IdT id);

    public abstract Optional<ObjectT> findById(IdT id);

    public abstract SearchResult<ObjectT> find(Long size, Long skip);

    public abstract Long count();

    public Optional<ObjectT> save(ObjectT object) {
        if (object.getCreatedAt() == null) {
            object.setCreatedAt(new Date());
        }
        object.setUpdatedAt(new Date());

        return saveToRepository(object);
    }

}
