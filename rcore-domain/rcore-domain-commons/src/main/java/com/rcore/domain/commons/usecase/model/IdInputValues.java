package com.rcore.domain.commons.usecase.model;

import com.rcore.domain.commons.usecase.UseCase;
import lombok.Value;

@Value(staticConstructor = "of")
public class IdInputValues<Id> implements UseCase.InputValues {
    private final Id id;
}
