package com.rcore.domain.commons.usecase.model;

import com.rcore.domain.commons.usecase.UseCase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@AllArgsConstructor(staticName = "of")
@Getter
public class SingletonEntityOutputValues<Entity> implements UseCase.OutputValues {
    private final Entity entity;
}
