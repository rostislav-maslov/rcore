package com.rcore.database.mongo.domain.token.port;

import com.rcore.database.mongo.base.ObjectIdGenerator;
import com.rcore.domain.base.port.impl.StringIdGenerator;
import com.rcore.domain.token.port.RefreshTokenIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class RefreshTokenIdGeneratorImpl extends ObjectIdGenerator implements RefreshTokenIdGenerator<ObjectId> {
}
