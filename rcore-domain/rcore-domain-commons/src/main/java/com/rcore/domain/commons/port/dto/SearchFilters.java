package com.rcore.domain.commons.port.dto;

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
    private SortDirection sortDirection = SortDirection.DESC;

    public enum SortDirection {
        ASC, DESC;

        public static SortDirection fromString(String sortDirection) {
            if (sortDirection == null)
                return DESC;

            var s = sortDirection.toUpperCase();

            return valueOf(s);
        }
    }
}
