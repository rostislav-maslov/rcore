package com.rcore.rest.api.commons.request;

import com.rcore.domain.commons.port.dto.SearchFilters;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class SearchApiRequest {
    private String query = "";
    private Long limit = 20l;
    private Long offset = 0l;
    private String sortName = "id";
    private SortDirection sortDirection = SortDirection.DESC;

    public enum SortDirection {
        ASC, DESC
    }

    public SearchFilters toSearchFilters() {
        return SearchFilters.builder()
                .query(query)
                .limit(limit)
                .offset(offset)
                .sortName(sortName)
                .sortDirection(sortDirection.name())
                .build();
    }
}
