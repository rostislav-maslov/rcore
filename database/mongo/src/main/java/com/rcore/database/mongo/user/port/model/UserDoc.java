package com.rcore.database.mongo.user.port.model;

import com.rcore.domain.user.entity.UserEntity;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * TODO прописать индекты
 */
@Document
@CompoundIndexes({
        @CompoundIndex(def = "{'_id': 1}", name = "_id_")
})
public class UserDoc extends UserEntity {

}
