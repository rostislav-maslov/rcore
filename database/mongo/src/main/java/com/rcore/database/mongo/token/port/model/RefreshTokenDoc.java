package com.rcore.database.mongo.token.port.model;

import com.rcore.domain.token.entity.RefreshTokenEntity;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@CompoundIndexes({
        @CompoundIndex(def = "{'_id': 1}", name = "_id_")
})
public class RefreshTokenDoc extends RefreshTokenEntity {
}
