package com.rcore.domain.base.port;

import lombok.*;

import java.util.Map;

@AllArgsConstructor
@NoArgsConstructor(staticName = "of")
@Builder
@Data
public class SearchRequest {
    private String query;
    private Long limit;
    private Long offset;
    private String sortName;
    private String sortDirection = "DESC";
}
