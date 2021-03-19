package com.rcore.domain.commons.usecase.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(staticName = "of")
@Getter
public class SingleOutput<Value> {
    private final Value value;
}
