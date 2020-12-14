package com.rcore.domain.commons.usecase.model;

import com.rcore.domain.commons.port.dto.SearchFilters;
import com.rcore.domain.commons.usecase.UseCase;
import lombok.Value;

@Value(staticConstructor = "of")
public class FiltersInputValues<Filters extends SearchFilters> implements UseCase.InputValues {
    public final Filters filters;
}
