package com.rcore.event.driven;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class DefaultEventDispatcherTests {

    @Test
    void DefaultEventDispatcher_RegisterManyHandlerForOneEvent_Successful() {
        EventDispatcher eventDispatcher = new DefaultEventDispatcher();
        eventDispatcher.registerHandler(TestEvent.class, new TestEventHandler());
        eventDispatcher.registerHandler(TestEvent.class, new TestEventLoggingHandler());
    }

    @Test
    void DefaultEventDispatcher_DispatchEventWithStoreInDB_Successful() {
        InMemoryEventStorage inMemoryEventStorage = new InMemoryEventStorage();
        EventDispatcher eventDispatcher = DefaultEventDispatcher.withStorage(inMemoryEventStorage);
        eventDispatcher.registerHandler(TestEvent.class, new TestEventHandler());

        eventDispatcher.dispatch(new TestEvent());

        Assertions.assertEquals(inMemoryEventStorage.database.size(), 1);
    }

    private static class InMemoryEventStorage implements EventStorage {

        public final HashMap<String, Event> database = new HashMap<>();

        @Override
        public <E extends Event> void put(E event) {
            database.put(UUID.randomUUID().toString(), event);
        }
    }

    private static class TestEvent extends AbstractEvent {

    }

    private static class TestEventHandler implements EventHandler<TestEvent> {

        @Override
        public void onEvent(TestEvent event) {
            System.out.println("TestEventHandler handle event");
        }
    }

    private static class TestEventLoggingHandler implements EventHandler<TestEvent> {
        @Override
        public void onEvent(TestEvent event) {
            System.out.println("TestEventLoggingHandler handle event");
        }
    }

}