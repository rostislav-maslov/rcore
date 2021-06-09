package com.rcore.domain.commons.usecase.model;

import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.commons.usecase.UseCase;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

@AllArgsConstructor(staticName = "of")
@Getter
public class SingletonOptionalEntityOutputValues<Entity extends BaseEntity> implements UseCase.OutputValues {

    private final Optional<Entity> entity;

    public static SingletonOptionalEntityOutputValues empty(){
        return SingletonOptionalEntityOutputValues.of(Optional.empty());
    }
}
