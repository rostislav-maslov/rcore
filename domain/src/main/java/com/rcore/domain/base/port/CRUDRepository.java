package com.rcore.domain.base.port;

import java.util.Optional;

public interface CRUDRepository<IdT,ObjectT> {

    Optional<ObjectT> save(ObjectT object);
    Boolean delete(ObjectT object);
    Boolean deleteById(IdT id);
    Optional<ObjectT> findById(IdT id);
    SearchResult<ObjectT> find(Long size, Long skip);
    Long count();

}
