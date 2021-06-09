package com.rcore.domain.commons.usecase.model;

import com.rcore.domain.commons.usecase.UseCase;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;

@Valid
@AllArgsConstructor(staticName = "of")
@Getter
public class SingleInput<Value> implements UseCase.InputValues {
    @Valid
    private final Value value;
}
