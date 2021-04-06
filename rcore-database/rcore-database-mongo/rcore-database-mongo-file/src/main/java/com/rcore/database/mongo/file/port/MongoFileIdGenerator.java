package com.rcore.database.mongo.file.port;

import com.rcore.database.mongo.commons.port.impl.ObjectIdGenerator;
import com.rcore.domain.file.port.FileIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class MongoFileIdGenerator  extends ObjectIdGenerator implements FileIdGenerator<ObjectId> {
}