package com.rcore.event.driven;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.UUID;

class DefaultEventDispatcherTests {

    @Test
    void DefaultEventDispatcher_RegisterManyHandlerForOneEvent_Successful() {
        EventDispatcher eventDispatcher = new DefaultEventDispatcher();
        eventDispatcher.registerHandler(SuperEvent.class, new TestEventHandler<>());
    }

    @Test
    void DefaultEventDispatcher_DispatchEventWithStoreInDB_Successful() {
        InMemoryEventStorage inMemoryEventStorage = new InMemoryEventStorage();
        EventDispatcher eventDispatcher = DefaultEventDispatcher.withStorage(inMemoryEventStorage);
        eventDispatcher.registerHandler(Test1Event.class, new TestEventHandler<>());
        eventDispatcher.registerHandler(Test2Event.class, new TestEventHandler<>());

        eventDispatcher.dispatch(new Test1Event());
        eventDispatcher.dispatch(new Test2Event());

        Assertions.assertEquals(inMemoryEventStorage.database.size(), 2);
    }

    private static class InMemoryEventStorage implements EventStorage {

        public final HashMap<String, Event> database = new HashMap<>();

        @Override
        public <E extends Event> void put(E event) {
            database.put(UUID.randomUUID().toString(), event);
        }
    }

    private static class SuperEvent extends AbstractEvent {

    }

    private static class Test1Event extends SuperEvent {

    }

    private static class Test2Event extends SuperEvent {

    }

    private static class TestEventHandler <T extends SuperEvent> implements EventHandler<T> {

        @Override
        public void onEvent(T event) {
            System.out.println("TestEventHandler handle event");
        }
    }

    private static class TestEventLoggingHandler <T extends SuperEvent> implements EventHandler<T> {
        @Override
        public void onEvent(SuperEvent event) {
            System.out.println("TestEventLoggingHandler handle event");
        }
    }



}