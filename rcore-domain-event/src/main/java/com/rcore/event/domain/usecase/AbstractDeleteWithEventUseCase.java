package com.rcore.event.domain.usecase;

import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.commons.port.DeleteRepository;
import com.rcore.domain.commons.port.ReadRepository;
import com.rcore.domain.commons.usecase.AbstractExtendedDeleteUseCase;
import com.rcore.event.domain.event.AbstractEntityDeleteEvent;
import com.rcore.event.driven.EventDispatcher;

/**
 * Класс для запуска евента после удаления
 */
public abstract class AbstractDeleteWithEventUseCase<ID, R extends ReadRepository<ID, E, ?> & DeleteRepository<ID>, E extends BaseEntity<ID>>
        extends AbstractExtendedDeleteUseCase<ID, R, E> {
    protected final EventDispatcher eventDispatcher;

    public AbstractDeleteWithEventUseCase(R repository, EventDispatcher eventDispatcher) {
        super(repository);
        this.eventDispatcher = eventDispatcher;
    }

    @Override
    protected final void after(E e) {
        eventDispatcher.dispatch(deleteEvent(e));
    }

    protected abstract AbstractEntityDeleteEvent<E> deleteEvent(E entity);
}
