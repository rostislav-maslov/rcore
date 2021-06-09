package com.rcore.domain.commons.usecase.model;

import com.rcore.domain.commons.port.dto.SearchFilters;
import com.rcore.domain.commons.usecase.UseCase;
import lombok.Value;

import javax.validation.Valid;

@Valid
@Value(staticConstructor = "of")
public class FiltersInputValues<Filters extends SearchFilters> implements UseCase.InputValues {
    @Valid
    Filters filters;
}
