package com.rcore.domain.auth.authorization.usecases;

import com.rcore.domain.auth.authorization.entity.AuthorizationEntity;
import com.rcore.domain.auth.authorization.port.AuthorizationRepository;
import com.rcore.domain.commons.port.SearchFilters;
import com.rcore.domain.commons.port.SearchResult;
import com.rcore.domain.commons.usecase.UseCase;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@RequiredArgsConstructor
public class FindAuthorizationsWithFiltersUseCase extends UseCase<FindAuthorizationsWithFiltersUseCase.InputValues, FindAuthorizationsWithFiltersUseCase.OutputValues> {

    private final AuthorizationRepository authorizationRepository;

    @Override
    public FindAuthorizationsWithFiltersUseCase.OutputValues execute(FindAuthorizationsWithFiltersUseCase.InputValues inputValues) {
        return new FindAuthorizationsWithFiltersUseCase.OutputValues(authorizationRepository.find(inputValues.getFilters()));
    }

    @Value
    public static class InputValues implements UseCase.InputValues {
        private final SearchFilters filters;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValues {
        private final SearchResult<AuthorizationEntity> result;
    }

}
