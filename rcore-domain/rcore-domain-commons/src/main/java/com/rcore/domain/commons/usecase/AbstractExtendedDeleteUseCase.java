package com.rcore.domain.commons.usecase;

import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.commons.port.DeleteRepository;
import com.rcore.domain.commons.port.ReadRepository;

/**
 * Абстрактный класс для удаляющего use case.
 * С возможностью переопределить метод который запускается перед и после удаления.
 *
 * @param <Id>         - Тип идентификатора сущности
 * @param <Entity>     - Тип сущности
 * @param <Repository> - удаляющий репозиторий
 */
public abstract class AbstractExtendedDeleteUseCase<Id, Repository extends DeleteRepository<Id> & ReadRepository<Id, Entity, ?>, Entity extends BaseEntity<Id>> extends AbstractActionWithEntityUseCase<Id, Repository, Entity> {
    public AbstractExtendedDeleteUseCase(Repository repository) {
        super(repository);
    }

    @Override
    protected final boolean execute(Entity entity) {
        return repository.delete(entity.getId());
    }
}
