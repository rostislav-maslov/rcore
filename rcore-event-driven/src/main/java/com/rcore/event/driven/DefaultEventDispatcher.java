package com.rcore.event.driven;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 * Default event dispatcher
 */
@Slf4j
@NoArgsConstructor
public class DefaultEventDispatcher extends EventDispatcher {
    protected final Map<Class<? extends Event>, List<EventHandler<? extends Event>>> handlers = new HashMap<>();

    private DefaultEventDispatcher(EventStorage eventStorage) {
        super(eventStorage);
    }

    public static DefaultEventDispatcher withStorage(EventStorage eventStorage) {
        return new DefaultEventDispatcher(eventStorage);
    }

    @Override
    public <E extends Event> void registerHandler(Class<? extends E> eventType, EventHandler<? extends E> handler) {
        List<EventHandler<? extends Event>> registeredHandlersForEvent = handlers.get(eventType);
        if (registeredHandlersForEvent != null) {
            boolean handlerBeenRegistered = registeredHandlersForEvent
                    .stream()
                    .anyMatch(h -> !h.getClass().equals(handler.getClass()));
            if (handlerBeenRegistered)
                registeredHandlersForEvent.add(handler);
        } else
            handlers.put(eventType, new ArrayList<>(Collections.singletonList(handler)));
    }

    @Override
    public <E extends Event> void dispatch(E event) {
        if (getEventStorage() != null)
            getEventStorage().put(event);

        List<EventHandler<? extends Event>> targetEventHandlers = Objects.requireNonNullElse(handlers.get(event.getClass()), new ArrayList<>());

        targetEventHandlers
                .forEach(handler -> {
                    ((EventHandler<E>) handler).onEvent(event);
                    log.debug("Event {} dispatched to handler {} ", event.getType(), handler.toString());
                });

    }
}
