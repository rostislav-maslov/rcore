package com.rcore.database.mongo.auth.token.port;

import com.rcore.database.mongo.commons.port.impl.ObjectIdGenerator;
import com.rcore.domain.auth.token.port.AccessTokenIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class MongoAccessTokenIdGenerator extends ObjectIdGenerator implements AccessTokenIdGenerator<ObjectId> {
}
