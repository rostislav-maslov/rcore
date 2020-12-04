package com.rcore.domain.commons.usecase.all;

import com.rcore.domain.commons.port.DeleteRepository;
import com.rcore.domain.commons.usecase.IDeleteUseCase;

public abstract class DeleteUseCase<Id, Repository extends DeleteRepository<Id>>
        implements IDeleteUseCase<Id> {

    protected final Repository repository;

    public DeleteUseCase(Repository repository) {
        this.repository = repository;
    }

    @Override
    public Boolean delete(Id id) {
        return repository.delete(id);
    }
}
