package com.rcore.domain.commons.usecase;

public interface IUpdateUseCase<Entity, Command> {
    Entity update(Command command) throws Exception;
}
