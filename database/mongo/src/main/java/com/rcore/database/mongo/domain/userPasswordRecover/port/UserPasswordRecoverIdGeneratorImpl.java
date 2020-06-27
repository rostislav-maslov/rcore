package com.rcore.database.mongo.domain.userPasswordRecover.port;

import com.rcore.database.mongo.base.ObjectIdGenerator;
import com.rcore.domain.user.port.UserIdGenerator;
import com.rcore.domain.userPasswordRecover.port.UserPasswordRecoverIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class UserPasswordRecoverIdGeneratorImpl extends ObjectIdGenerator implements UserPasswordRecoverIdGenerator<ObjectId> {
}
