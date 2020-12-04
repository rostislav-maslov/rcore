package com.rcore.domain.commons.usecase;

public interface ICreateUseCase<Entity, Command> {
    Entity create(Command command) throws Exception;
}
