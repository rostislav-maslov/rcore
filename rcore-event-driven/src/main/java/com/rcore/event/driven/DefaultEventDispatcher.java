package com.rcore.event.driven;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

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
    public <E extends Event> void registerHandler(Class<E> eventType, EventHandler<? super E> handler) {
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

        //noinspection unchecked
        Class<E> eventClass = (Class<E>) event.getClass();

        //noinspection SuspiciousMethodCalls
        Set<EventHandler<? extends Event>> targetEventHandlers = getSuperEventClasses(eventClass)
                .stream()
                .map(handlers::get)
                .filter(Objects::nonNull)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());


        sendEventToHandlers(targetEventHandlers, event);
    }

    @Override
    protected <E extends Event> void sendEventToHandlers(Collection<EventHandler<? extends Event>> targetEventHandlers, E event) {
        targetEventHandlers
                .forEach(handler -> {
                    //noinspection unchecked
                    ((EventHandler<E>) handler).onEvent(event);
                    log.debug("Event {} dispatched to handler {} ", event.getType(), handler);
                });
    }

    /**
     * Возвращает все классы родительские классы которые есть у евента(Вплоть до AbstractEvent)
     */
    private <E extends Event> Set<Class<? super E>> getSuperEventClasses(Class<E> eventClass) {
        Set<Class<? super E>> classSet = new HashSet<>();

        Class<? super E> latestClazz = eventClass;

        while (!latestClazz.equals(AbstractEvent.class)) {
            classSet.add(latestClazz);

            latestClazz = latestClazz.getSuperclass();
        }

        return classSet;
    }
}
