package com.rcore.database.mongo.eventDriven.port;

import com.rcore.database.mongo.commons.utils.CollectionNameUtils;
import com.rcore.database.mongo.eventDriven.document.EventDoc;
import com.rcore.event.driven.Event;
import com.rcore.event.driven.EventStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class MongoEventStorage implements EventStorage {

    private final MongoTemplate mongoTemplate;

    @Override
    public <E extends Event> void put(E event) {
        mongoTemplate.save(EventDoc.by(event), CollectionNameUtils.getCollectionName(EventDoc.class));
    }
}
