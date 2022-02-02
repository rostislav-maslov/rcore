package com.rcore.rest.api.commons.request;

import com.rcore.domain.commons.port.dto.SearchFilters;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class SearchApiRequest {
    private String query = "";
    private Long limit = 20L;
    private Long offset = 0L;
    private String sortName = "id";
    private SearchFilters.SortDirection sortDirection = SearchFilters.SortDirection.DESC;

    public SearchFilters toSearchFilters() {
        return fill(SearchFilters.builder(), this).build();
    }

    public static <B extends SearchFilters.SearchFiltersBuilder<?, ?>> B fill(B builder, SearchApiRequest request) {
        //noinspection unchecked
        return (B) builder
                .query(request.query)
                .limit(request.limit)
                .offset(request.offset)
                .sortName(request.sortName)
                .sortDirection(request.sortDirection);
    }
}
