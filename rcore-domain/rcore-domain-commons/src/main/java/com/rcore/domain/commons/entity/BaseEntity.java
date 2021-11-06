package com.rcore.domain.commons.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
public abstract class BaseEntity<EntityId> {
    protected EntityId id;
    protected Instant createdAt = Instant.now();
    protected Instant updatedAt = Instant.now();


}
