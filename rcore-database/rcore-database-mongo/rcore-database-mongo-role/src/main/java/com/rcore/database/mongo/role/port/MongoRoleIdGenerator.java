package com.rcore.database.mongo.role.port;

import com.rcore.database.mongo.commons.port.impl.ObjectIdGenerator;
import com.rcore.domain.role.port.RoleIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class MongoRoleIdGenerator extends ObjectIdGenerator implements RoleIdGenerator<ObjectId> {
}
