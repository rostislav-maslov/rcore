package com.rcore.domain.commons.usecase.model;

import com.rcore.domain.commons.usecase.UseCase;
import lombok.Value;

@Value(staticConstructor = "of")
public class SingletonEntityOutputValues<Entity> implements UseCase.OutputValues {
    private final Entity entity;
}
