package com.rcore.domain.commons.usecase.model;

import com.rcore.domain.commons.usecase.UseCase;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;

@AllArgsConstructor(staticName = "of")
@Getter
public class SingleOutput<Value> implements UseCase.OutputValues {
    private final Value value;
}
