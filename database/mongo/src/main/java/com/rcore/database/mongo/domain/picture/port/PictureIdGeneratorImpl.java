package com.rcore.database.mongo.domain.picture.port;

import com.rcore.database.mongo.base.ObjectIdGenerator;
import com.rcore.domain.base.port.BaseIdGenerator;
import org.bson.types.ObjectId;

public class PictureIdGeneratorImpl extends ObjectIdGenerator implements BaseIdGenerator<ObjectId> {
}
