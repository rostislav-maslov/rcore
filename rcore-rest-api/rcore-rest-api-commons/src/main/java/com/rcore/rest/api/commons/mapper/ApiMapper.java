package com.rcore.rest.api.commons.mapper;

import com.rcore.commons.mapper.DataMapper;
import com.rcore.domain.commons.port.dto.SearchResult;
import com.rcore.domain.commons.usecase.model.SearchResultEntityOutputValues;
import com.rcore.domain.commons.usecase.model.SingleOutput;
import com.rcore.rest.api.commons.response.SearchApiResponse;
import com.rcore.rest.api.commons.response.SuccessApiResponse;

import java.util.stream.Collectors;

public abstract class ApiMapper<DomainModel, ApiResponse> implements DataMapper<DomainModel, ApiResponse> {

    public SuccessApiResponse<SearchApiResponse<ApiResponse>> mapToSearchApiResponse(SearchResult<DomainModel> result) {
        return SuccessApiResponse.of(SearchApiResponse.withItemsAndCount(
                result.getItems().stream().map(this::map).collect(Collectors.toList()),
                result.getCount()
        ));
    };

    public SuccessApiResponse<SearchApiResponse<ApiResponse>> mapToSearchApiResponse(SingleOutput<SearchResult<DomainModel>> result) {
        return SuccessApiResponse.of(SearchApiResponse.withItemsAndCount(
                result.getValue().getItems().stream().map(this::map).collect(Collectors.toList()),
                result.getValue().getCount()
        ));
    };

    public SuccessApiResponse<SearchApiResponse<ApiResponse>> mapToSearchApiResponse(SearchResultEntityOutputValues<DomainModel> result) {
        return SuccessApiResponse.of(SearchApiResponse.withItemsAndCount(
                result.getResult().getItems().stream().map(this::map).collect(Collectors.toList()),
                result.getResult().getCount()
        ));
    };

    public SuccessApiResponse<ApiResponse> mapToSingleResponse(SingleOutput<DomainModel> result) {
        return SuccessApiResponse.of(map(result.getValue()));
    };



}
