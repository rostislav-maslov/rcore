package com.rcore.domain.base.port;

public interface CRUDRepository<IdT,ObjectT> {

    public ObjectT save(ObjectT object);
    public Boolean delete(ObjectT object);
    public Boolean deleteById(IdT id);
    public ObjectT findById(IdT id);
    public Long count();

}
