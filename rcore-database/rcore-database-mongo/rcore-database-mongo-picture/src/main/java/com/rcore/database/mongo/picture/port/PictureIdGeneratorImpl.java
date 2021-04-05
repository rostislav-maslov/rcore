package com.rcore.database.mongo.picture.port;

import com.rcore.database.mongo.commons.port.impl.ObjectIdGenerator;
import com.rcore.domain.picture.port.PictureIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class PictureIdGeneratorImpl extends ObjectIdGenerator implements PictureIdGenerator<ObjectId> {
}
