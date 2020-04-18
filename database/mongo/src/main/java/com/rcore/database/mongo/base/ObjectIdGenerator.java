package com.rcore.database.mongo.base;

import com.rcore.domain.base.exception.InvalidIdException;
import com.rcore.domain.base.port.BaseIdGenerator;
import com.rcore.domain.user.port.UserIdGenerator;
import org.bson.types.ObjectId;

public class ObjectIdGenerator implements BaseIdGenerator<ObjectId> {

    @Override
    public String generate() {
        return new ObjectId().toString();
    }

    @Override
    public ObjectId parse(String id) throws InvalidIdException {
        if (!ObjectId.isValid(id))
            throw new InvalidIdException();

        return new ObjectId(id);
    }
}
