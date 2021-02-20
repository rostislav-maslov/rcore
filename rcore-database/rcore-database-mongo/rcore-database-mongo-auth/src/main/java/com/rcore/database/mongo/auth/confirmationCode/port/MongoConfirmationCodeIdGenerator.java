package com.rcore.database.mongo.auth.confirmationCode.port;

import com.rcore.database.mongo.commons.port.impl.ObjectIdGenerator;
import com.rcore.domain.auth.confirmationCode.port.ConfirmationCodeIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class MongoConfirmationCodeIdGenerator extends ObjectIdGenerator implements ConfirmationCodeIdGenerator<ObjectId> {
}
