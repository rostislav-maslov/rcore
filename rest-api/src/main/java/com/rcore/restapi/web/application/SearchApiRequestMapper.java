package com.rcore.restapi.web.application;

import com.rcore.commons.mapper.ExampleDataMapper;
import com.rcore.domain.base.port.SearchRequest;
import com.rcore.restapi.web.api.request.SearchApiRequest;
import org.springframework.stereotype.Component;

@Component
public class SearchApiRequestMapper implements ExampleDataMapper<SearchRequest, SearchApiRequest> {

    @Override
    public SearchApiRequest map(SearchRequest searchRequest) {
        return SearchApiRequest.builder()
                .limit(searchRequest.getLimit())
                .offset(searchRequest.getOffset())
                .query(searchRequest.getQuery())
                .sortDirection(searchRequest.getSortDirection())
                .sortName(searchRequest.getSortName())
                .build();
    }

    @Override
    public SearchRequest inverseMap(SearchApiRequest request) {
        return SearchRequest.builder()
                .limit(request.getLimit())
                .sortDirection(request.getSortDirection())
                .limit(request.getLimit())
                .query(request.getQuery())
                .offset(request.getOffset())
                .sortName(request.getSortName())
                .build();
    }
}
