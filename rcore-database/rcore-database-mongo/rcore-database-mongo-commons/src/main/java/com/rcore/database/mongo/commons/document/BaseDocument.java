package com.rcore.database.mongo.commons.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.time.Instant;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class BaseDocument {
    @Id
    protected ObjectId id = new ObjectId();
    protected Instant createdAt = Instant.now();
    protected Instant updatedAt = Instant.now();
}
