package com.rcore.domain.commons.port;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Data
public class SearchFilters {
    private String query;
    private Long limit;
    private Long offset;
    private String sortName;
    private String sortDirection = "DESC";
}
