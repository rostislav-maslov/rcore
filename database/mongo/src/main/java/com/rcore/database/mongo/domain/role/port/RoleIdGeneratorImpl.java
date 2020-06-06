package com.rcore.database.mongo.domain.role.port;

import com.rcore.database.mongo.base.ObjectIdGenerator;
import com.rcore.domain.role.port.RoleIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class RoleIdGeneratorImpl extends ObjectIdGenerator implements RoleIdGenerator<ObjectId> {
}
