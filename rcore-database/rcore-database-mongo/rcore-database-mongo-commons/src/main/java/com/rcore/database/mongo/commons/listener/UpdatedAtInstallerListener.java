package com.rcore.database.mongo.commons.listener;

import com.rcore.database.mongo.commons.document.BaseDocument;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeSaveEvent;
import org.springframework.data.mongodb.core.mapping.event.MongoMappingEvent;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Objects;

@Component
public class UpdatedAtInstallerListener extends AbstractMongoEventListener<BaseDocument> {

    @Override
    public void onApplicationEvent(MongoMappingEvent<?> event) {
        if (event instanceof BeforeSaveEvent) {
            BeforeSaveEvent<?> e = (BeforeSaveEvent<?>) event;
            if (e.getSource() instanceof BaseDocument) {
                Objects.requireNonNull(e.getDocument())
                        .put("updatedAt", Instant.now());
            }

        }
    }
}
