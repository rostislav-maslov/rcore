package com.rcore.domain.commons.usecase.model;

import com.rcore.domain.commons.entity.BaseEntity;
import com.rcore.domain.commons.port.dto.SearchResult;
import com.rcore.domain.commons.usecase.UseCase;
import lombok.Value;

@Value(staticConstructor = "of")
public class SearchResultEntityOutputValues<Entity extends BaseEntity> implements UseCase.OutputValues {
    private final SearchResult<Entity> result;
}
