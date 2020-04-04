package com.rcore.memory.base.port;

import com.rcore.domain.base.port.CRUDRepository;

import java.util.HashMap;
import java.util.Map;

public abstract class CRUDRepositoryImpl<IdT, ObjectT> implements CRUDRepository<IdT, ObjectT> {

    protected Map<IdT, ObjectT> container = new HashMap<>();

    @Override
    public Long count() {
        return (long)container.size();
    }
}
