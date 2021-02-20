package com.rcore.database.mongo.auth.authorization.port;

import com.rcore.database.mongo.commons.port.impl.ObjectIdGenerator;
import com.rcore.domain.auth.authorization.port.AuthorizationIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class MongoAuthorizationIdGenerator extends ObjectIdGenerator implements AuthorizationIdGenerator<ObjectId> {
}
