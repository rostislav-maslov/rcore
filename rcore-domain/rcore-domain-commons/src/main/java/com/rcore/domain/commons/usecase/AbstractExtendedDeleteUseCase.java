package com.rcore.domain.commons.usecase;

import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.commons.port.CRUDRepository;
import com.rcore.domain.commons.usecase.model.IdInputValues;
import lombok.Value;

import java.util.Optional;

/**
 * Абстрактный класс для удаляющего use case.
 * С возможностью переопределить метод который запускается перед и после удаления.
 *
 * @param <Id>         - Тип идентификатора сущности
 * @param <Entity>     - Тип сущности
 * @param <Repository> - удаляющий репозиторий
 */
public abstract class AbstractExtendedDeleteUseCase<Id, Entity extends BaseEntity<Id>, Repository extends CRUDRepository<Id, Entity, ?>> extends UseCase<IdInputValues<Id>, AbstractExtendedDeleteUseCase.OutputValues> {
    protected final Repository repository;

    public AbstractExtendedDeleteUseCase(Repository repository) {
        this.repository = repository;
    }

    /**
     * Вызывается перед удалением. Можно переопределить.
     *
     * @return если вернуть false, то удаление не произойдёт
     */
    public boolean before(Entity entity) {
        return true;
    }

    @Override
    public OutputValues execute(IdInputValues<Id> idInputValues) {
        boolean idDeleted = false;
        Optional<Entity> optional = repository.findById(idInputValues.getId());
        if (optional.isEmpty())
            return OutputValues.of(false);

        if (before(optional.get()))
            idDeleted = repository.delete(idInputValues.getId());

        if (idDeleted)
            after(optional.get());

        return OutputValues.of(idDeleted);
    }

    /**
     * Вызывается после удаления. Можно переопределить.
     */
    public void after(Entity entity) {
    }


    @Value(staticConstructor = "of")
    public static class OutputValues implements UseCase.OutputValues {
        Boolean result;
    }
}
