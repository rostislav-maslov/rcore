package com.rcore.database.mongo.domain.file.port;

import com.rcore.database.mongo.base.ObjectIdGenerator;
import com.rcore.domain.base.port.BaseIdGenerator;
import com.rcore.domain.file.port.FileIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class FileIdGeneratorImpl extends ObjectIdGenerator implements FileIdGenerator<ObjectId> {
}
