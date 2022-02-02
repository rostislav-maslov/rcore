package com.rcore.event.domain.event;

import com.rcore.domain.commons.entity.BaseEntity;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public abstract class AbstractEntityUpdateEvent<E extends BaseEntity<?>> extends AbstractEntityEvent<E> {
    public AbstractEntityUpdateEvent(E entity) {
        super(entity);
    }
}
