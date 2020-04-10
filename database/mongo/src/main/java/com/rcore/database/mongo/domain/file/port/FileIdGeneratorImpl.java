package com.rcore.database.mongo.domain.file.port;

import com.rcore.database.mongo.base.ObjectIdGenerator;
import com.rcore.domain.base.port.BaseIdGenerator;
import org.bson.types.ObjectId;

public class FileIdGeneratorImpl extends ObjectIdGenerator implements BaseIdGenerator<ObjectId> {
}
