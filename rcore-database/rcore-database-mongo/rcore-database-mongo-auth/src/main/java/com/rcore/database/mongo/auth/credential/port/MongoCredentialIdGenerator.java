package com.rcore.database.mongo.auth.credential.port;

import com.rcore.database.mongo.commons.port.impl.ObjectIdGenerator;
import com.rcore.domain.auth.credential.port.CredentialIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class MongoCredentialIdGenerator extends ObjectIdGenerator implements CredentialIdGenerator<ObjectId> {
}
