package com.rcore.database.mongo.eventDriven.document;

import com.rcore.event.driven.Event;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventDoc {
    private String id = new ObjectId().toString();
    private LocalDateTime createdAt = LocalDateTime.now();
    private Event event;

    public EventDoc(Event event) {
        this.event = event;
    }

    public static EventDoc by(Event event) {
        return new EventDoc(event);
    }
}
