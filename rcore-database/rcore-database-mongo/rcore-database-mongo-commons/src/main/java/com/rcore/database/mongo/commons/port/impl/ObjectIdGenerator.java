package com.rcore.database.mongo.commons.port.impl;

import com.rcore.domain.commons.exception.InvalidIdException;
import com.rcore.domain.commons.port.BaseIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class ObjectIdGenerator implements BaseIdGenerator<ObjectId> {

    @Override
    public String generate() {
        return new ObjectId().toString();
    }

    @Override
    public ObjectId parse(String id) throws InvalidIdException {
        if (!ObjectId.isValid(id))
            throw new InvalidIdException(id);

        return new ObjectId(id);
    }
}
