package com.rcore.domain.commons.port;

public interface DeleteRepository<Id> {
    Boolean delete(Id id);
}
