package com.rcore.domain.commons.usecase;

import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.commons.port.BaseIdGenerator;
import com.rcore.domain.commons.port.CreateUpdateRepository;
import com.rcore.domain.commons.usecase.model.SingletonEntityOutputValues;
import lombok.Value;

/**
 * Абстрактный класс для создающего use case
 * @param <Entity> - Класс создаваемой сущности
 * @param <IdGenerator> - Класс генератора id
 * @param <Repository> - сохраняющий репозиторий
 * @param <InputValues> - Входящие знаачения
 */
public abstract class AbstractCreateUseCase<Entity extends BaseEntity, IdGenerator extends BaseIdGenerator, Repository extends CreateUpdateRepository<Entity>, InputValues extends UseCase.InputValues>
        extends UseCase<InputValues, SingletonEntityOutputValues<Entity>> {

    protected final Repository repository;
    protected final IdGenerator idGenerator;

    public AbstractCreateUseCase(Repository repository, IdGenerator idGenerator) {
        this.repository = repository;
        this.idGenerator = idGenerator;
    }

}
