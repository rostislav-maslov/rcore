package com.rcore.domain.commons.usecase;

public interface IDeleteUseCase<Id> {
    Boolean delete(Id id);
}
