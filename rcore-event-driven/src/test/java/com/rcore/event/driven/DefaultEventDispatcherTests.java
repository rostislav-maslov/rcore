package com.rcore.event.driven;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.UUID;

public class DefaultEventDispatcherTests {

    /**
     * Тест проверяет что event обработался 2 раза, как мы его и кидали
     */
    @Test
    void DefaultEventDispatcher_RegisterOneHandlerForMultiplyEvent_Successful() {
        EventDispatcher eventDispatcher = new DefaultEventDispatcher();
        eventDispatcher.registerHandler(ParentEvent.class, new CallbackEventHandler());

        Callback callback = Mockito.mock(Callback.class);

        eventDispatcher.dispatch(new ParentEvent(callback));
        eventDispatcher.dispatch(new ParentEvent(callback));

        Mockito.verify(callback, Mockito.times(2)).call();
    }

    /**
     * Проверяем что если установить класс event'а ниже чем Parent, то есть его наследуемый класс, то Parent вызываться не будет
     * поскольку нас интересует конкретно ChildEvent
     */
    @Test
    void DefaultEventDispatcher_VerifyNoExcessEventFire() {
        EventDispatcher eventDispatcher = new DefaultEventDispatcher();

        // Слушаем только ChildEvent, и его наследников, ParentEvent тут не слушается
        eventDispatcher.registerHandler(ChildEvent.class, new CallbackEventHandler());

        Callback needCall = Mockito.mock(Callback.class);
        Callback doesNotCall = Mockito.mock(Callback.class);

        eventDispatcher.dispatch(new ChildEvent(needCall));
        eventDispatcher.dispatch(new ParentEvent(doesNotCall));

        Mockito.verify(needCall, Mockito.times(1)).call();
        Mockito.verify(doesNotCall, Mockito.times(0)).call();
    }

    /**
     * В этом тесте наоборот, ставим класс event ниже всего(CallbackEvent), и пытаемся вызвать разные евенты
     */
    @Test
    void DefaultEventDispatcher_VerifySuperEventFire() {
        EventDispatcher eventDispatcher = new DefaultEventDispatcher();

        // Слушаем всё что наследуется от CallbackEvent
        eventDispatcher.registerHandler(CallbackEvent.class, new CallbackEventHandler());

        Callback callback = Mockito.mock(Callback.class);

        eventDispatcher.dispatch(new ParentEvent(callback));
        eventDispatcher.dispatch(new ChildEvent(callback));

        // Проверяем что и там и там отработало
        Mockito.verify(callback, Mockito.times(2)).call();

    }

    @Test
    void DefaultEventDispatcher_DispatchEventWithStoreInDB_Successful() {
        InMemoryEventStorage inMemoryEventStorage = new InMemoryEventStorage();
        EventDispatcher eventDispatcher = DefaultEventDispatcher.withStorage(inMemoryEventStorage);


        eventDispatcher.registerHandler(Test1Event.class, new SuperEventHandler<>());
        eventDispatcher.registerHandler(Test2Event.class, new SuperEventHandler<>());

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

    private abstract static class SuperEvent extends AbstractEvent {

    }

    private static class Test1Event extends SuperEvent {

    }

    private static class Test2Event extends SuperEvent {

    }

    @RequiredArgsConstructor
    private abstract static class CallbackEvent extends SuperEvent {
        private final Callback callback;
    }

    private static class ParentEvent extends CallbackEvent {

        public ParentEvent(Callback callback) {
            super(callback);
        }
    }

    private static class ChildEvent extends ParentEvent {

        public ChildEvent(Callback callback) {
            super(callback);
        }
    }

    private static class SuperEventHandler<T extends SuperEvent> implements EventHandler<T> {

        @Override
        public void onEvent(T event) {
            System.out.println("Event " + event.getClass().getSimpleName() + " fired");
        }
    }

    private static class CallbackEventHandler extends SuperEventHandler<CallbackEvent> {

        @Override
        public void onEvent(CallbackEvent event) {
            super.onEvent(event);
            if (event.callback != null)
                event.callback.call();
        }
    }

    private interface Callback {
        void call();
    }
}