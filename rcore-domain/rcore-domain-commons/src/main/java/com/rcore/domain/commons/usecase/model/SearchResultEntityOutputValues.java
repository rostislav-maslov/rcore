package com.rcore.domain.commons.usecase.model;

import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.commons.port.dto.SearchResult;
import com.rcore.domain.commons.usecase.UseCase;
import lombok.Value;

import javax.validation.Valid;

@Valid
@Value(staticConstructor = "of")
public class SearchResultEntityOutputValues<Entity> implements UseCase.OutputValues {
    @Valid
    private final SearchResult<@Valid Entity> result;
}
