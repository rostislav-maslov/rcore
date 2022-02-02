package com.rcore.event.domain.event;

import com.rcore.domain.commons.entity.BaseEntity;
import lombok.experimental.SuperBuilder;

@SuperBuilder
public abstract class AbstractEntityDeleteEvent<E extends BaseEntity<?>> extends AbstractEntityEvent<E> {
    public AbstractEntityDeleteEvent(E entity) {
        super(entity);
    }
}
