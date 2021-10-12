package com.rcore.database.mongo.eventDriven.document;

import com.rcore.event.driven.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventDoc {
    private String id = new ObjectId().toString();
    private Instant createdAt = Instant.now();
    private Event payload;
    private String type;

    public EventDoc(Event event) {
        this.payload = event;
        this.type = event.getType();
    }

    public static EventDoc by(Event event) {
        return new EventDoc(event);
    }
}
