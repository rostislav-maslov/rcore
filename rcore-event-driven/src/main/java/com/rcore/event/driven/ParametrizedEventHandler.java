package com.rcore.event.driven;

public abstract class ParametrizedEventHandler<T extends Event> implements EventHandler<T> {

    protected final Class<T> typeParameterClass;

    public ParametrizedEventHandler(Class<T> typeParameterClass) {
        this.typeParameterClass = typeParameterClass;
    }
}
