package com.rcore.domain.commons.usecase;

import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.commons.port.CreateUpdateRepository;

/**
 * Абстрактный класс для обновляющего use case
 * @param <Entity> - Класс создаваемой сущности
 * @param <Repository> - обновляющий репозиторий
 * @param <InputValues> - Входящие знаачения
 */
public abstract class AbstractUpdateUseCase<Entity extends BaseEntity, Repository extends CreateUpdateRepository<Entity>, InputValues extends UseCase.InputValues>
        extends UseCase<InputValues, AbstractCreateUseCase.OutputValues<Entity>> {

    protected final Repository repository;

    public AbstractUpdateUseCase(Repository repository) {
        this.repository = repository;
    }
}
