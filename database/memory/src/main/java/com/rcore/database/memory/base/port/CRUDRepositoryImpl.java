package com.rcore.database.memory.base.port;

import com.rcore.domain.base.entity.BaseEntity;
import com.rcore.domain.base.port.CRUDRepository;

import java.util.HashMap;
import java.util.Map;

public abstract class CRUDRepositoryImpl<IdT, ObjectT extends BaseEntity> implements CRUDRepository<IdT, ObjectT> {

    protected Map<IdT, ObjectT> container = new HashMap<>();

    @Override
    public Long count() {
        return (long)container.size();
    }
}
