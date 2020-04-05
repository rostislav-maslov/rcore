package com.rcore.domain.base.port;

public interface CRUDRepository<IdT,ObjectT> {

    public ObjectT save(ObjectT object);
    public Boolean delete(ObjectT object);
    public Boolean deleteById(IdT id);
    public ObjectT findById(IdT id);
    public SearchResult<ObjectT> find(Long size, Long skip);
    public Long count();

}
