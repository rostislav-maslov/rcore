package com.rcore.database.mongo.auth.token.port;

import com.rcore.database.mongo.commons.port.impl.ObjectIdGenerator;
import com.rcore.domain.auth.token.port.RefreshTokenIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class MongoRefreshTokenIdGenerator extends ObjectIdGenerator implements RefreshTokenIdGenerator<ObjectId> {
}
