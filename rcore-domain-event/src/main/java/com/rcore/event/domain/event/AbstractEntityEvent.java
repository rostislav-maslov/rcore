package com.rcore.event.domain.event;

import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.event.driven.AbstractEvent;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@RequiredArgsConstructor
public abstract class AbstractEntityEvent<E extends BaseEntity<?>> extends AbstractEvent {
    protected final E entity;
}
