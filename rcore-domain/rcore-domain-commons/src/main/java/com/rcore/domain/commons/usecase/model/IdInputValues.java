package com.rcore.domain.commons.usecase.model;

import com.rcore.domain.commons.usecase.UseCase;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

import javax.validation.Valid;

@Valid
@AllArgsConstructor(staticName = "of")
@Getter
public class IdInputValues<Id> implements UseCase.InputValues {
    private final Id id;
}
