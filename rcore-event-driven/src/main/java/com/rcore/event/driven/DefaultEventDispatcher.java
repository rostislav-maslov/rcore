package com.rcore.event.driven;

import lombok.NoArgsConstructor;

import java.util.*;

/**
 * Default event dispatcher
 */
@NoArgsConstructor
public class DefaultEventDispatcher extends EventDispatcher {
    private final Map<Class<? extends Event>, List<EventHandler<? extends Event>>> handlers = new HashMap<>();

    public DefaultEventDispatcher(EventStorage eventStorage) {
        super(eventStorage);
    }

    @Override
    public <E extends Event> void registerHandler(Class<E> eventType, EventHandler<E> handler) {
        List<EventHandler<? extends Event>> registeredHandlersForEvent = handlers.get(eventType);
        if (registeredHandlersForEvent != null) {
            boolean handlerBeenRegistered = registeredHandlersForEvent
                    .stream()
                    .anyMatch(h -> !h.getClass().equals(handler.getClass()));
            if (handlerBeenRegistered)
                registeredHandlersForEvent.add(handler);
        } else
            handlers.put(eventType, new ArrayList<>(Arrays.asList(handler)));
    }

    @Override
    public <E extends Event> void dispatch(E event) {
        if (getEventStorage() != null)
            getEventStorage().put(event);

        handlers.get(event.getClass())
                .forEach(handler ->
                        ((EventHandler<E>) handler).onEvent(event));
    }
}
