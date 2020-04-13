package com.rcore.database.mongo.domain.picture.port;

import com.rcore.database.mongo.base.ObjectIdGenerator;
import com.rcore.domain.base.port.BaseIdGenerator;
import com.rcore.domain.picture.port.PictureIdGenerator;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

@Component
public class PictureIdGeneratorImpl extends ObjectIdGenerator implements PictureIdGenerator<ObjectId> {
}
