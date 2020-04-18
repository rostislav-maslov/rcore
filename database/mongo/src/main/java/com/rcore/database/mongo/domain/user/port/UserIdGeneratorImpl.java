package com.rcore.database.mongo.domain.user.port;

import com.rcore.database.mongo.base.ObjectIdGenerator;
import com.rcore.domain.user.port.UserIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class UserIdGeneratorImpl extends ObjectIdGenerator implements UserIdGenerator<ObjectId> {
}
